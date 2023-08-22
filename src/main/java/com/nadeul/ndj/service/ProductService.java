package com.nadeul.ndj.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.ProductDto;
import com.nadeul.ndj.entity.Product;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService<T> {
	
	 private final ProductRepository productRepository;
	  
	  public ApiResponse<List<ProductDto>> list(Pageable pageable) {
		  Page<Product> data = productRepository.findAll(pageable);
		   List<ProductDto> dataList = data.getContent().stream()
		            .map(product -> {
		                ProductDto dto = new ProductDto();
		                dto.setPdId(product.getPdId());
		                dto.setName(product.getName());
		                dto.setStockQty(product.getStockQty());
		                dto.setThumbnailUrl(product.getThumbnailUrl());
		                dto.setShortDescription(product.getShortDescription());
		                dto.setProductType(product.getProductType().getTitle());
		                dto.setPoint(product.getPoint());
		                dto.setCreateDate(product.getCreateDate());
		                dto.setCreateBy(product.getCreateBy());
		                dto.setUpdateDate(product.getUpdateDate());
		                dto.setUpdateBy(product.getUpdateBy());
		                dto.setDescription(product.getDescription());
		                return dto;
		            })
		            .collect(Collectors.toList());
		   
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, dataList, null, null);
	  }
	  
	  public ApiResponse<Product> getPostById(Integer id) {
		  Optional<Product> postOptional = productRepository.findById(id);
		  if (postOptional.isPresent()) {
			  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, postOptional.get(), null, null);
		  }
		  return ApiResponse.failResponse(ApiResponseEnum.NOT_FOUND, null);
	  }
}
