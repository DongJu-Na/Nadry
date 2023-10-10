package com.nadeul.ndj.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
public class PointHistory {
	
  @Id
  @GeneratedValue
  private Integer phId;
  
  private LocalDateTime useDate;
  
  private Integer usePoint;
  
  private String usedBy;
  
  @ManyToOne
  @JoinColumn(name = "pdId", referencedColumnName = "pdId")
  private Product product;
  
  @ManyToOne
  @JoinColumn(name = "poId", referencedColumnName = "poId")
  private Point point;
  
  private String remarks;
		
}
