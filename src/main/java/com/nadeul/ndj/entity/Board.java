package com.nadeul.ndj.entity;

import java.util.Date;
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
public class Board {
	
  @Id
  @GeneratedValue
  private Integer boId;
  
  private String boardName;
  
  private String managerYn;
  
  private String delYn;
  
  private Date createDate;
  
  private String createBy;
  
  private Date updateDate;
  
  private String updateBy;
  
  @OneToMany
  @JoinColumn(name = "boId")
  private List<Post> post;
		
		
}
