package com.nadeul.ndj.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReviewLike {
	
  @Id
  @GeneratedValue
  private Integer rlId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rvId", insertable = true, updatable = true)
  private Review review;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memId", insertable = true, updatable = true)
  private Member member;
  
  private int likes;
  
  private LocalDateTime likeDate;
  
}
