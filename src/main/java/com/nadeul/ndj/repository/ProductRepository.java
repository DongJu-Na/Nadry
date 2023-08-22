package com.nadeul.ndj.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
  Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
  
}
