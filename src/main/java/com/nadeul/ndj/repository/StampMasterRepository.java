package com.nadeul.ndj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.StampMaster;

public interface StampMasterRepository extends JpaRepository<StampMaster, Integer> {
	Optional<StampMaster> findByStampType(String stampType);
}
