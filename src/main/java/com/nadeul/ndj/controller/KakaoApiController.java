package com.nadeul.ndj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.KakaoDto;
import com.nadeul.ndj.dto.MemberDto;
import com.nadeul.ndj.service.KakaoApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "KakaoService", description = "카카오 API")
@RequestMapping("/api/v1/kakao")
@RequiredArgsConstructor
@RestController
public class KakaoApiController<T> {
	
	private final KakaoApiService<T> service;
	
	@PostMapping("/authenticate")
	@Operation(summary = "카카오 로그인", description = "카카오 계정으로 로그인")
    public ResponseEntity<ApiResponse<MemberDto>> kakaoAuthenticate(@RequestBody KakaoDto request) {
        return ResponseEntity.ok(service.kakaoAuthenticate(request));
    }

}
