package com.nadeul.ndj.dto;

import com.nadeul.ndj.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
	
  private Integer id;
  private String name;
  private String email;
  private String password;
  private String birthDay;
  private Role role;
  
  
}
