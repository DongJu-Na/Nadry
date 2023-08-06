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
public class Comment {
	
  @Id
  @GeneratedValue
  private Integer cmId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "boId", insertable = true, updatable = true)
  private Board board;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ptId", insertable = true, updatable = true)
  private Post post;
  
  private String content;
  
  private int likes;
  
  private String delYn;
  
  private Date createDate;
  
  private String createBy;
  
  private Date updateDate;
  
  private String updateBy;
  
		
		
}
