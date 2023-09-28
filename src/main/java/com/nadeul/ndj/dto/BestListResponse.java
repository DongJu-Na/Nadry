package com.nadeul.ndj.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BestListResponse {
  @Schema(description = "실시간 베스트 여행 리뷰 목록" , example = "객체 반환")
  private Integer rvId;
  private String contentId;
  private String content;
  private String reviewImageUrl;
  private LocalDateTime createDate;
  private String createBy;
  private LocalDateTime updateDate;
  private String updateBy;
  private Object likes;
  private Object rating;
  private Integer memId;
  private String name;
  private String email;
  private String profileUrl;
  
  public BestListResponse(Integer rvId, String contentId, String content, String reviewImageUrl,
          LocalDateTime createDate, String createBy, LocalDateTime updateDate, String updateBy, Object likes
          ,Object rating,Integer memId,String name,String email,String profileUrl) {
	    this.rvId = rvId;
        this.contentId = contentId;
        this.content = content;
        this.reviewImageUrl = reviewImageUrl;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
        this.likes = likes;
        this.rating = rating;
        this.memId = memId;
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
  }
} 