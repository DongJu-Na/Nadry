package com.nadeul.ndj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.AuthenticationRequest;
import com.nadeul.ndj.dto.PointEarnDto;
import com.nadeul.ndj.service.PointService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "PointService", description = "인증/인가 서비스 API")
@RestController
@RequestMapping("/api/v2/point")
@RequiredArgsConstructor
public class PointController<T> {
  
	private final PointService service;
	
  @PostMapping("/earn")
  @Operation(summary = "포인트 적립", description = "나드리 포인트 적립")
  public ResponseEntity<ApiResponse<PointEarnDto>> earn( @RequestBody AuthenticationRequest request ) {
    return ResponseEntity.ok(null);
  }
  
  @PostMapping("/use")
  @Operation(summary = "포인트 사용", description = "나드리 포인트 사용")
  public ResponseEntity<ApiResponse<T>> use( @RequestBody AuthenticationRequest request ) {
    return ResponseEntity.ok(null);
  }
  
}
