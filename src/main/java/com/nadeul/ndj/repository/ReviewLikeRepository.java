package com.nadeul.ndj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.ReviewLike;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Integer> {
   
}
