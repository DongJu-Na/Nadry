package com.nadeul.ndj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
}
