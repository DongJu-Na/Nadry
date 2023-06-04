package com.nadeul.ndj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  
  private int like;
  
  private Date likeDate;
  
  private String likeYn;
  
  private Date createDate;
  
  private String createBy;
  
  private Date updateDate;
  
  private String updateBy;
  
		
		
}
