package com.nadeul.ndj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.ReviewLike;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Integer> {
	Optional<ReviewLike> findByMemberMemIdAndReviewRvId(Integer memId, String rvId);
}
