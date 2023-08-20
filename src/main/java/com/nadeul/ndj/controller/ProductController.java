package com.nadeul.ndj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.entity.Product;
import com.nadeul.ndj.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "ProductService", description = "상품 서비스 API")
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController<T> {
	
	private final ProductService<T> productService;
	
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Product>>> list(Pageable pageable) {
        return ResponseEntity.ok(productService.list(pageable));
    }
	
}
