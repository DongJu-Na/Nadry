package com.nadeul.ndj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	
}
