package com.nadeul.ndj.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Stamp {
	
  @Id
  @GeneratedValue
  private Integer stId;
  
  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "memId")
  private Member member;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "stmId")
  private StampMaster stampMaster;
  
  private LocalDateTime stampCollectDate;
  
  
}

