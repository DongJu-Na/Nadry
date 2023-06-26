package com.nadeul.ndj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
	
}
