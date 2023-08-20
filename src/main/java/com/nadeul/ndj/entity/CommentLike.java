package com.nadeul.ndj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentLike {
	
  @Id
  @GeneratedValue
  private Integer clId;
  
  private Integer bdId;
  
  private Integer ptId;
  
  private Integer cmId;
  
  private int likes;
  
  private Date likeDate;
  
  private String likeYn;
  
  private LocalDateTime createDate;
  
  private String createBy;
  
  private LocalDateTime updateDate;
  
  private String updateBy;
  
		
		
}
