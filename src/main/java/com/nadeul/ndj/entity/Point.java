package com.nadeul.ndj.entity;

import jakarta.persistence.Entity;
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
  
  private Integer point;
  private String blackYn;
  
  @OneToOne
  @JoinColumn(name = "memId")
  private Member member;
  
  public Point update(Integer point, String blackYn) {
    this.point = point;
    this.blackYn = blackYn;
    return this;
  }
		
}
