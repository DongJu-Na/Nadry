package com.nadeul.ndj.entity;

import java.time.LocalDateTime;

import com.nadeul.ndj.enums.ProductType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	
  @Id
  @GeneratedValue
  private Integer pdId;
  
  private String name;
  
  private Integer stockQty;
  
  private String thumbnailUrl;
  
  private String shortDescription;
  
  private String Description;
  
  @Enumerated(EnumType.STRING)
  private ProductType productType;
  
  private Integer point;
  
  private LocalDateTime createDate;
  
  private String createBy;
  
  private LocalDateTime updateDate;
  
  private String updateBy;
  
  
  
  

}
