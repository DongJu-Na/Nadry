package com.nadeul.ndj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.entity.Product;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService<T> {
	
	 private final ProductRepository productRepository;
	  
	  public ApiResponse<List<Product>> list(Pageable pageable) {
		  System.out.println("test 1");
		  Page<Product> data = productRepository.findAll(pageable);
		  System.out.println("test 2");
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, data.getContent(), null, null);
	  }
	  
	  public ApiResponse<Product> getPostById(Integer id) {
		  Optional<Product> postOptional = productRepository.findById(id);
		  if (postOptional.isPresent()) {
			  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, postOptional.get(), null, null);
		  }
		  return ApiResponse.failResponse(ApiResponseEnum.NOT_FOUND, null);
	  }
}
