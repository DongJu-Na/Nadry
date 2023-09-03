package com.nadeul.ndj.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
public class ReviewGrade {
	
  @Id
  @GeneratedValue
  private Integer rgId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rvId", insertable = true, updatable = true)
  private Review review;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memId")
  private Member member;

  @Column(name = "rating", precision = 2, scale = 1)
  private BigDecimal rating;

  private LocalDateTime ratingDate;
  
}
