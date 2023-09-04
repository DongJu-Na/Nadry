package com.nadeul.ndj.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nadeul.ndj.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	 Page<Review> findByContentId(String contentId,Pageable pageable);
	 
	 @Query("SELECT AVG(rg.rating) FROM Review r JOIN r.reviewGrade rg WHERE r.contentId = :contentId")
	 BigDecimal findAverageRatingByContentId(@Param("contentId") String contentId);
	 
     @Query("SELECT COUNT(r.rvId) FROM Review r WHERE r.contentId = :contentId AND r.createBy = :createBy")
     int countReviewsByContentIdAndCreateBy(@Param("contentId") String contentId, @Param("createBy") String createBy);
	 
	 Optional<Review> findByRvIdAndContentIdAndMember_MemId(Integer rvId, String contentId, Integer memId);
}
