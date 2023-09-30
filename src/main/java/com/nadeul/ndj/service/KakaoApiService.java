package com.nadeul.ndj.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.KakaoDto;
import com.nadeul.ndj.dto.MemberDto;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.entity.Token;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.model.Role;
import com.nadeul.ndj.model.TokenType;
import com.nadeul.ndj.repository.MemberRepository;
import com.nadeul.ndj.repository.TokenRepository;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoApiService<T> {
	
	private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String REDIRECT_URI;
    
		
      @SuppressWarnings("unchecked")
	  public ApiResponse<MemberDto> kakaoAuthenticate(KakaoDto request) {
		  
    	  String kakaoAccessToken = getKaKaoAccessToken(request.getCode());
    	  HashMap<String, Object> kakaoInfo = getUserKakaoInfo(kakaoAccessToken);
    	  
    	  
  		  String email = (String) kakaoInfo.get("email");
  		  
  		  Optional<Member> existingMember = memberRepository.findByEmail(email);
  		  
  		if(existingMember.isPresent()) {
  			Member member = Member.builder()
  				  .memId(existingMember.get().getMemId())
  				  .name(kakaoInfo.get("name").toString())
  		          .email(kakaoInfo.get("email").toString())
  		          .profileUrl(kakaoInfo.get("profileUrl").toString())
  		          .build();
  			
  			var savedUser = memberRepository.save(member);
  			
  			String jwtToken = jwtService.generateToken(existingMember.get());
        	String refreshToken = jwtService.generateRefreshToken(existingMember.get());
        	List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(existingMember.get().getMemId());
      	    validUserTokens.forEach(token -> {
      	      token.setExpired(true);
      	      token.setRevoked(true);
      	    });
      	    tokenRepository.saveAll(validUserTokens);
      	    
      	    Token token = Token.builder()
    	            .member(existingMember.get())
    	            .token(jwtToken)
    	            .tokenType(TokenType.BEARER)
    	            .expired(false)
    	            .revoked(false)
    	            .build();
    	        tokenRepository.save(token);   
    	    
    	     MemberDto memberDto = new MemberDto();
  			  memberDto.setMemId(existingMember.get().getMemId());
  			  memberDto.setName(existingMember.get().getName());
  			  memberDto.setEmail(existingMember.get().getEmail());
  			  memberDto.setPassword(existingMember.get().getPassword());
  			  memberDto.setBirthDay(existingMember.get().getBirthDay());
  			  memberDto.setRole(existingMember.get().getRole());
  			  memberDto.setProfileUrl(existingMember.get().getProfileUrl());
  			  
  			return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,memberDto,jwtToken,refreshToken);
  		}else {
  			 Member member = Member.builder()
  				
  		          .name(kakaoInfo.get("name").toString())
  		          .email(kakaoInfo.get("email").toString())
  		          .password(null)
  		          .role(Role.USER)
  		          .birthDay(null)
  		          .profileUrl(kakaoInfo.get("profileUrl").toString())  
  		          .build();
  		      var savedUser = memberRepository.save(member);
  		      var jwtToken = jwtService.generateToken(member);
  		      var refreshToken = jwtService.generateRefreshToken(member);
  		      
  		      MemberDto memberDto = new MemberDto();
			  memberDto.setMemId(member.getMemId());
			  memberDto.setName(member.getName());
			  memberDto.setEmail(member.getEmail());
			  memberDto.setPassword(member.getPassword());
			  memberDto.setBirthDay(member.getBirthDay());
			  memberDto.setRole(member.getRole());
			  memberDto.setProfileUrl(member.getProfileUrl());
  		      
  		    return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,memberDto,jwtToken,refreshToken);
  		}
  		
	  }
      
      
      public String getKaKaoAccessToken(String code) {

    	    String access_Token = "";
    	    String refresh_Token = "";
    	    String reqURL = "https://kauth.kakao.com/oauth/token";

    	    try {
    	        URL url = new URL(reqURL);
    	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    	        conn.setRequestMethod("POST");
    	        conn.setDoOutput(true);

    	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
    	        StringBuilder sb = new StringBuilder();
    	        sb.append("grant_type=authorization_code");
    	        sb.append("&client_id=" + CLIENT_ID);
    	        sb.append("&redirect_uri=" + REDIRECT_URI);
    	        sb.append("&code=" + code);
    	        bw.write(sb.toString());
    	        bw.flush();

    	        int responseCode = conn.getResponseCode();
    	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	        String line = "";
    	        String result = "";

    	        while ((line = br.readLine()) != null) {
    	            result += line;
    	        }
    	        
    	        ObjectMapper objectMapper = new ObjectMapper();
    	        JsonNode rootNode = objectMapper.readTree(result);
    	        
    	        access_Token = rootNode.get("access_token").asText();
    	        refresh_Token = rootNode.get("refresh_token").asText();

    	        br.close();
    	        bw.close();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }

    	    return access_Token;
    	}
      
      public HashMap<String, Object> getUserKakaoInfo(String access_Token) {

    	    HashMap<String, Object> userInfo = new HashMap<String, Object>();
    	    String reqURL = "https://kapi.kakao.com/v2/user/me";
    	    try {
    	        URL url = new URL(reqURL);
    	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	        conn.setRequestMethod("GET");

    	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);

    	        int responseCode = conn.getResponseCode();

    	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

    	        String line = "";
    	        String result = "";

    	        while ((line = br.readLine()) != null) {
    	            result += line;
    	        }

    	        ObjectMapper objectMapper = new ObjectMapper();
    	        JsonNode rootNode = objectMapper.readTree(result.toString());

    	        String id = rootNode.path("id").asText();

    	        JsonNode propertiesNode = rootNode.path("properties");
    	        JsonNode kakaoAccountNode = rootNode.path("kakao_account");

    	        String nickname = propertiesNode.path("nickname").asText();
    	        if (kakaoAccountNode.has("email")) {
    	            String email = kakaoAccountNode.path("email").asText();
    	            userInfo.put("email", email);
    	        }

    	        userInfo.put("name", nickname);
    	        userInfo.put("id", id);
    	        userInfo.put("profileUrl", propertiesNode.path("thumbnail_image").asText());
    	        userInfo.put("role", Role.USER);

    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	    return userInfo;
    	}
}

