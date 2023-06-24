package com.nadeul.ndj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.PointEarnDto;
import com.nadeul.ndj.dto.PointUseDto;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.service.PointService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "PointService", description = "포인트 서비스 API")
@RestController
@RequestMapping("/api/v2/point")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class PointController<T> {
  
	private final PointService<T> service;
	
  @PostMapping("/earn")
  @Operation(summary = "포인트 적립", description = "나드리 포인트 적립")
  public ResponseEntity<ApiResponse<PointEarnDto>> earn( @RequestBody PointEarnDto request ) {
  	
  	if(request.getPosX() == null || request.getPosX().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"위도"));
  	}
  	
  	if(request.getPosY() == null || request.getPosY().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"경도"));
  	}
  	
    return ResponseEntity.ok(null);
  }
  
  @PostMapping("/use")
  @Operation(summary = "포인트 사용", description = "나드리 포인트 사용")
  public ResponseEntity<ApiResponse<T>> use( @RequestBody PointUseDto request ) {
  	
  	if(request.getPosX() == null || request.getPosX().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"위도"));
  	}
  	
  	if(request.getPosY() == null || request.getPosY().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"경도"));
  	}
  	
    return ResponseEntity.ok(null);
  }
  
}
