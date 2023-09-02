package com.nadeul.ndj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
	
}
