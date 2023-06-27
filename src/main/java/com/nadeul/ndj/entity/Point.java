package com.nadeul.ndj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Point {
	
  @Id
  @GeneratedValue
  private Integer poId;
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memId", insertable = true, updatable = true)
  private Member member;
  
  private Integer point;
  private String blackYn;
  
  
  
  public Point update(Integer point, String blackYn) {
    this.point = point;
    this.blackYn = blackYn;
    return this;
  }
		
}
