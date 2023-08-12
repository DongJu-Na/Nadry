package com.nadeul.ndj.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.AuthenticationRequest;
import com.nadeul.ndj.dto.MemberDto;
import com.nadeul.ndj.dto.RegisterRequest;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.model.Role;
import com.nadeul.ndj.service.AuthenticationService;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationService<MemberDto> authenticationService;

    @InjectMocks
    private AuthenticationController<MemberDto> authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("deprecation")
	@Test
    void testRegister() {
        // 테스트용 RegisterRequest 객체 생성
        RegisterRequest registerRequest = new RegisterRequest("John Doe", "example@example.com", "password", Role.USER, "1990-01-01",null);
        
        // Mockito를 사용하여 AuthenticationService의 register() 메서드 모킹
        // 적절한 ApiResponse를 반환하도록 설정
        when(authenticationService.register(any(RegisterRequest.class)))
                .thenReturn(ApiResponse.successResponse(ApiResponseEnum.SUCCESS, new MemberDto(), "access_token", "refresh_token"));

        // 테스트용 RegisterRequest를 요청 본문으로 하는 HTTP POST 요청 전송
        ResponseEntity<ApiResponse<MemberDto>> response = authenticationController.register(registerRequest,null);

        // 테스트 결과 검증
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assertions.assertEquals(ApiResponseEnum.SUCCESS.getCode(), response.getBody().getResultCode());
        Assertions.assertEquals("성공", response.getBody().getResultMsg());
        Assertions.assertEquals("access_token", response.getBody().getAccessToken());
        Assertions.assertEquals("refresh_token", response.getBody().getRefreshToken());

        // 등록된 사용자의 정보를 확인하는 등의 추가적인 검증도 가능합니다.
    }

    @SuppressWarnings("deprecation")
	@Test
    void testAuthenticate() {
        // 테스트용 AuthenticationRequest 객체 생성
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("example@example.com", "password");
        
        // Mockito를 사용하여 AuthenticationService의 authenticate() 메서드 모킹
        // 적절한 ApiResponse를 반환하도록 설정
        when(authenticationService.authenticate(any(AuthenticationRequest.class)))
                .thenReturn(ApiResponse.successResponse(ApiResponseEnum.SUCCESS, new MemberDto(), "access_token", "refresh_token"));

        // 테스트용 AuthenticationRequest를 요청 본문으로 하는 HTTP POST 요청 전송
        ResponseEntity<ApiResponse<MemberDto>> response = authenticationController.authenticate(authenticationRequest);

        // 테스트 결과 검증
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assertions.assertEquals(ApiResponseEnum.SUCCESS.getCode(), response.getBody().getResultCode());
        Assertions.assertEquals("성공", response.getBody().getResultMsg());
        Assertions.assertEquals("access_token", response.getBody().getAccessToken());
        Assertions.assertEquals("refresh_token", response.getBody().getRefreshToken());
        // 인증된 사용자의 정보를 확인하는 등의 추가적인 검증도 가능합니다.
    }
}