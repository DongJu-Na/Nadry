package com.nadeul.ndj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.DibsDto;
import com.nadeul.ndj.entity.Dibs;
import com.nadeul.ndj.service.DibsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "DibsService", description = "찜 서비스 API")
@RestController
@RequestMapping("/api/v2/dibs")
@RequiredArgsConstructor
public class DibsController<T> {
	
	private final DibsService<T> service;
	
	@GetMapping("/dibsList")
	@Operation(summary = "관광지 찜 목록 조회", description = "나의 찜 목록 조회")
    public ResponseEntity<ApiResponse<List<Dibs>>> list(DibsDto.ListRequest request) {
        return ResponseEntity.ok(service.list(request));
    }
	
    @PostMapping("/")
    @Operation(summary = "관광지 찜", description = "관광지 찜 추가")
    public ResponseEntity<ApiResponse<T>> dibs(@RequestBody DibsDto.Request request) {
    	return ResponseEntity.ok(service.dibs(request));
    }


}
