package com.nadeul.ndj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
  
  private Integer memId;
  
  private Integer point;
  
  private String blackYn;
  
		
		
}
