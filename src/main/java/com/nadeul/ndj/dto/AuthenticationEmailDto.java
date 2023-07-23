package com.nadeul.ndj.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationEmailDto {
	
  @Schema(description = "이메일" , example = "ehdeld123@gmail.com")
  private String email;
  
}
