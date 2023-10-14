package com.nadeul.ndj.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "OrderService", description = "주문 서비스 API")
@RestController
@RequestMapping("/api/v2/order")
@RequiredArgsConstructor
public class OrderController<T> {
	
	private final OrderService<T> orderService;
	
	@GetMapping("/myOrderList")
	@Operation(summary = "주문 내역 조회", description = "주문 내역 목록 조회")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> myOrderList() {
        return ResponseEntity.ok(orderService.myOrderList());
    }
	
	
}
