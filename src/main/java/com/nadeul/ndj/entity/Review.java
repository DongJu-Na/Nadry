package com.nadeul.ndj.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
  
  private LocalDateTime createDate;
  
  private String createBy;
  
  private LocalDateTime updateDate;
  
  private String updateBy;
  
  @OneToMany(mappedBy="review")
  private List<ReviewLike> reviewLike;
  
  @OneToMany(mappedBy="review")
  private List<ReviewGrade> reviewGrade;
  
  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "memId")
  private Member member;
		
}
