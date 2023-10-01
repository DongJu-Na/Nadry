package com.nadeul.ndj.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nadeul.ndj.dto.BestListResponse;
import com.nadeul.ndj.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	 Page<Review> findByContentId(String contentId,Pageable pageable);
	 
	 Page<Review> findByMemberMemId(Integer memId, Pageable pageable);
	 
	 @Query("SELECT TRUNCATE(AVG(rg.rating), 1) FROM Review r JOIN r.reviewGrade rg WHERE r.contentId = :contentId")
	 BigDecimal findAverageRatingByContentId(@Param("contentId") String contentId);
	 
     @Query("SELECT COUNT(r.rvId) FROM Review r WHERE r.contentId = :contentId AND r.createBy = :createBy")
     int countReviewsByContentIdAndCreateBy(@Param("contentId") String contentId, @Param("createBy") String createBy);
     
     @Query("SELECT COUNT(rl.rlId) FROM Review r JOIN r.reviewLike rl WHERE r.contentId = :contentId")
     int countReviewsByContentId(@Param("contentId") String contentId);
	 
	 Optional<Review> findByRvIdAndContentIdAndMember_MemId(Integer rvId, String contentId, Integer memId);
	 
	 @Query("SELECT new com.nadeul.ndj.dto.BestListResponse(" +
		        "r.rvId, r.contentId, r.content, r.reviewImageUrl, " +
		        "r.createDate, r.createBy, r.updateDate, r.updateBy, " +
		        "COALESCE(SUM(rl.likes), 0) as likes, COALESCE(MAX(rg.rating), 0.0) as rating, " +
		        "m.memId, m.name, m.email, m.profileUrl) " +
		        "FROM Review r " +
		        "LEFT JOIN r.reviewLike rl " +
		        "LEFT JOIN r.reviewGrade rg " +
		        "LEFT JOIN r.member m " +
		        "GROUP BY r.rvId " +  // Added a space after r.rvId
		        "ORDER BY r.createDate DESC, likes DESC")
     Page<BestListResponse> findAllWithLikes(Pageable pageable);
     
}
