package com.nadeul.ndj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
	
  @Id
  @GeneratedValue
  private Integer ptId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "boId", insertable = true, updatable = true)
  private Board board;
  
  private String title;
  
  private String content;
  
  private int likes;
  
  private int view;
  
  private String delYn;
  
  private Date createDate;
  
  private String createBy;
  
  private Date updateDate;
  
  private String updateBy;
  
  private String contentId;
  
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "cmId", insertable = true, updatable = true)
  private List<Comment> comment;
  
  
		
		
}
