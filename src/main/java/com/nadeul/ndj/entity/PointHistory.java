package com.nadeul.ndj.entity;

import java.util.Date;

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
public class PointHistory {
	
  @Id
  @GeneratedValue
  private Integer phId;
  
  private Integer poId;
  
  private Date useDate;
  
  private Integer usePoint;
  
  private String usedBy;
  
		
		
}
