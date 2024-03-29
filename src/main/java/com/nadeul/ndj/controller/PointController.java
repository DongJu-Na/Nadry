package com.nadeul.ndj.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.PointEarnDto;
import com.nadeul.ndj.dto.PointHistoryDto;
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
@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
public class PointController<T> {
  
  private final PointService<T> service;
  
  @GetMapping("/myPointHistoryList")
  @Operation(summary = "포인트 사용내역 조회", description = "포인트 사용 내역 목록 조회")
  public ResponseEntity<ApiResponse<List<PointHistoryDto>>> myPointHistoryList(Pageable pageable) {
      return ResponseEntity.ok(service.myPointHistoryList(pageable));
  }
	
	
  @PostMapping("/earn")
  @Operation(summary = "포인트 적립", description = "나드리 포인트 적립")
  public ResponseEntity<ApiResponse<T>> earn( @RequestBody PointEarnDto request ) {
  	
  	if(request.getContentId() == null || request.getContentId().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"컨텐츠 아이디"));
  	}
  	
  	if(request.getPosX() == null || request.getPosX().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"위도"));
  	}
  	
  	if(request.getPosY() == null || request.getPosY().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"경도"));
  	}
  	
  	if(request.getPosY() == null || request.getPosY().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"실제 위도"));
  	}
  	
  	if(request.getPosY() == null || request.getPosY().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"실제 경도"));
  	}
  	
    return ResponseEntity.ok(service.earn(request));
  }
  
  @PostMapping("/use")
  @Operation(summary = "포인트 사용", description = "나드리 포인트 사용")
  public ResponseEntity<ApiResponse<T>> use( @RequestBody PointUseDto request ) {
	  
  	if(request.getPdId() == null || request.getPdId().toString().equals("")) {
  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"상품번호"));
  	}
  	
    return ResponseEntity.ok(service.use(request));
  }
  
}
