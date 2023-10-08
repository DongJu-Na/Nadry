package com.nadeul.ndj.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class StampMaster {
	
  @Id
  @GeneratedValue
  private Integer stmId;
  
  private String stampName;
  
  private LocalDateTime stampCreateDate; 
  
  private String stampImageUrl;
  
  private String stampType;
  
}

