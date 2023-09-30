package com.nadeul.ndj.oauth2;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nadeul.ndj.dto.MemberDto;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.entity.Token;
import com.nadeul.ndj.model.TokenType;
import com.nadeul.ndj.repository.MemberRepository;
import com.nadeul.ndj.repository.TokenRepository;
import com.nadeul.ndj.service.JwtService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("소셜 로그인 성공! 서버 로그를 확인해주세요.");
        /*
        try {
        		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        		log.info("oAuth2User 정보 : {} " , oAuth2User.toString());
        		
        		Map<String, Object> attributes = oAuth2User.getAttributes();
        		Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        		String email = (String) kakaoAccount.get("email");
        		
        		
        		Optional<Member> existingMember = userRepository.findByEmail(email);

            if(!existingMember.isPresent()) {
                response.sendRedirect("https://www.nadry.shop/login"); // 프론트의 회원가입 추가 정보 입력 폼으로 리다이렉트
            } else {
            	String jwtToken = jwtService.generateToken(existingMember.get());
            	String refreshToken = jwtService.generateRefreshToken(existingMember.get());
            	
            	var validUserTokens = tokenRepository.findAllValidTokenByUser(existingMember.get().getMemId());
            	if (validUserTokens.isEmpty())
            	      return;
            	    validUserTokens.forEach(token -> {
            	      token.setExpired(true);
            	      token.setRevoked(true);
            	    });
            	    tokenRepository.saveAll(validUserTokens);
        	    var token = Token.builder()
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
	  			  
	  			 response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	             response.setCharacterEncoding(StandardCharsets.UTF_8.name());
	  
	             PrintWriter writer = response.getWriter();
	             writer.println(memberDto);
	             writer.flush();
	  			 
            }
        } catch (Exception e) {
            throw e;
        }
        */
    }


}