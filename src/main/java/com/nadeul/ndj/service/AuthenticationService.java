package com.nadeul.ndj.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.AuthenticationEmailDto;
import com.nadeul.ndj.dto.AuthenticationRequest;
import com.nadeul.ndj.dto.AuthenticationResponse;
import com.nadeul.ndj.dto.MemberDto;
import com.nadeul.ndj.dto.RegisterRequest;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.entity.Token;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.model.TokenType;
import com.nadeul.ndj.repository.MemberRepository;
import com.nadeul.ndj.repository.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService<T> {
  private final MemberRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public ApiResponse<T> register(RegisterRequest request) {
    String email = request.getEmail();
    
    // 중복 이메일 확인
    Optional<Member> existingMember = repository.findByEmail(email);
    if (existingMember.isPresent()) {
        // 중복된 이메일이 있을 경우 토큰 발급 x
    	  // 에러코드 에러메세지 공통 정의 필요
    			return ApiResponse.failResponse(ApiResponseEnum.DUPLICATION,"이메일이");
    }
    
    var member = Member.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .birthDay(request.getBirthDay())
        .build();
    var savedUser = repository.save(member);
    var jwtToken = jwtService.generateToken(member);
    var refreshToken = jwtService.generateRefreshToken(member);
    saveUserToken(savedUser, jwtToken);
    
    return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,null,jwtToken,refreshToken);
  }

  public ApiResponse<MemberDto> authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    
    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    
    MemberDto memberDto = new MemberDto();
    					memberDto.setId(user.getMemId());
    					memberDto.setName(user.getName());
    					memberDto.setEmail(user.getEmail());
    					memberDto.setPassword(user.getPassword());
    					memberDto.setBirthDay(user.getBirthDay());
    					memberDto.setRole(user.getRole());
    
    return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,memberDto,jwtToken,refreshToken);
  }

  private void saveUserToken(Member member, String jwtToken) {
    var token = Token.builder()
        .member(member)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(Member user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getMemId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken( HttpServletRequest request, HttpServletResponse response ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
  
  public ApiResponse<T> emailCheck(AuthenticationEmailDto request) {
	    String email = request.getEmail();

	    // 중복 이메일 확인
	    Optional<Member> existingMember = repository.findByEmail(email);
	    if (existingMember.isPresent()) {
	        // 중복된 이메일이 있을 경우
	        return ApiResponse.failResponse(ApiResponseEnum.DUPLICATION, "이메일이 중복되었습니다.");
	    } else {
	        return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, null, null, null);
	    }
	}
  
}
