package com.nadeul.ndj.dto;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nadeul.ndj.entity.Review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Schema(description = "리뷰 DTO")
public class ReviewDto {
	
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ListRequest {
    	  @Schema(description = "리뷰 콘텐츠 번호" , example = "1")
    	  private String contentId;

    }

    @Getter
    public static class ListResponse {
	  @Schema(description = "여행 리뷰 목록" , example = "객체 반환")
	  private List<Review> review;
	  private BigDecimal averageRating;
	  private int totalLikes;
	  
	  public ListResponse(List<Review> review, BigDecimal averageRating, int totalLikes) {
	        this.review = review;
	        this.averageRating = averageRating;
	        this.totalLikes = totalLikes;
	  }

    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateUpdateDto {
    	@Schema(description = "리뷰 번호" , example = "1")
	    private String rvId;
    	@Schema(description = "리뷰 콘텐츠 번호" , example = "1")
	    private String contentId;
    	@Schema(description = "리뷰 내용" , example = "1")
	    private String content;
    	@Schema(description = "리뷰 사진" , example = "")
    	private MultipartFile reviewImage;
    	@Schema(description = "리뷰 평점" , example = "0.0" , defaultValue = "0.0")
    	private String reviewRating;

    }
    
}