package com.nadeul.ndj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stamp {
	
  @Id
  @GeneratedValue
  private Integer stId;
  
  private Integer memId;
  
  private Integer stmId;
  
  private Date stampCollectDate;
  
  
}

