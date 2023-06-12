package com.nadeul.ndj.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.AuthenticationRequest;
import com.nadeul.ndj.dto.MemberDto;
import com.nadeul.ndj.dto.RegisterRequest;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Tag(name = "AuthenticationService", description = "인증/인가 서비스 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController<T> {

  private final AuthenticationService<T> service;

  @PostMapping("/register")
  @Operation(summary = "회원 가입", description = "나들이 플랫폼 사용자 등록")
  public ResponseEntity<ApiResponse<T>> register( @RequestBody RegisterRequest request ) {
  	if(request.getEmail() == null || request.getEmail().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"이메일"));
  	}
  	
  	if(request.getName() == null || request.getName().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"이름"));
  	}
  	
  	if(request.getPassword() == null || request.getPassword().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"비밀번호"));
  	}
  	
  	if(request.getBirthDay() == null || request.getBirthDay().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"생일"));
  	}
  	
    return ResponseEntity.ok(service.register(request));
  }
  
  @PostMapping("/authenticate")
  @Operation(summary = "로그인", description = "나들이 서비스 인증")
  public ResponseEntity<ApiResponse<MemberDto>> authenticate( @RequestBody AuthenticationRequest request ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  @Operation(summary = "리프레시 토큰", description = "리프레시 토큰으로 액세스 토큰 갱신")
  public void refreshToken( HttpServletRequest request, HttpServletResponse response ) throws IOException {
    service.refreshToken(request, response);
  }


}
