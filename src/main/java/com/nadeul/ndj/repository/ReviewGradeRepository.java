package com.nadeul.ndj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.ReviewGrade;

public interface ReviewGradeRepository extends JpaRepository<ReviewGrade, Integer> {
   
}
