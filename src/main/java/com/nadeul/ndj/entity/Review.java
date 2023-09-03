package com.nadeul.ndj.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
	
  @Id
  @GeneratedValue
  private Integer rvId;
  
  private String contentId;
  
  private String content;
  
  private String reviewImageUrl;
  
  private int likes;
  
  private String delYn;
  
  private LocalDateTime createDate;
  
  private String createBy;
  
  private LocalDateTime updateDate;
  
  private String updateBy;
  
  @OneToMany
  @JoinColumn(name = "rlId")
  private List<ReviewLike> reviewLike;
  
  @OneToMany
  @JoinColumn(name = "rgId")
  private List<ReviewGrade> reviewGrade;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memId", insertable = true, updatable = true)
  private Member member;
		
}
