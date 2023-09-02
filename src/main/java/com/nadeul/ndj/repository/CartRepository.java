package com.nadeul.ndj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
}
