package com.nadeul.ndj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  
  @Schema(description = "액세스 토큰" , example = "")
  @JsonProperty("access_token")
  private String accessToken;
  
  @Schema(description = "리프레시 토큰" , example = "")
  @JsonProperty("refresh_token")
  private String refreshToken;
}
