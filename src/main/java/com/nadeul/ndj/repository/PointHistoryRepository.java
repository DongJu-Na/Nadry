package com.nadeul.ndj.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Integer> {
	Page<PointHistory> findByMemberMemId(Integer memId, Pageable pageable);
}
