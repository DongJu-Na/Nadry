package com.nadeul.ndj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.StampDto;
import com.nadeul.ndj.entity.Stamp;
import com.nadeul.ndj.service.StampService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "StampService", description = "스탬프 서비스 API")
@RestController
@RequestMapping("/api/v2/stamp")
@RequiredArgsConstructor
public class StampController<T> {
	
	private final StampService<T> stampService;
	
	@GetMapping("/myStampList")
	@Operation(summary = "스탬프 조회", description = "스탬프 목록 조회")
    public ResponseEntity<ApiResponse<List<StampDto>>> myStampList() {
        return ResponseEntity.ok(stampService.myStampList());
    }
	
	@PostMapping("/")
    public ResponseEntity<ApiResponse<Stamp>> createStamp(@RequestBody Stamp stamp) {
        return ResponseEntity.ok(stampService.createStamp(stamp));
    }
	
	
}
