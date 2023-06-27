package com.nadeul.ndj.entity;

import java.util.Date;

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
public class Trip {
	
  @Id
  @GeneratedValue
  private Integer tpId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memId", insertable = true, updatable = true)
  public Member member;
  
  private Float realPosX;
  
  private Float realPosY;
  
  private Float posX;
  
  private Float posY;
  
  private Date tripDate;
  
  private String contentId;
  
  
}
