package com.nadeul.ndj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nadeul.ndj.enums.ApiResponseEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * @FileName : ApiResponse.java
  * @Project : ndj
  * @Date : 2023. 6. 12. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : 공통 응답 객체
  */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
	
  private String resultCode;
  private String resultMsg;
  private T data;
  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("refresh_token")
  private String refreshToken;
  
  public static <T> ApiResponse<T> errorResponse(ApiResponseEnum responseEnum) {
    return ApiResponse.<T>builder()
            .resultCode(responseEnum.getCode())
            .resultMsg(responseEnum.getMessage())
            .data(null)
            .accessToken(null)
            .refreshToken(null)
            .build();
  }
  
  public static <T> ApiResponse<T> failResponse(ApiResponseEnum responseEnum , String AddonMsg) {
    return ApiResponse.<T>builder()
            .resultCode(responseEnum.getCode())
            .resultMsg(AddonMsg + " " + responseEnum.getMessage())
            .data(null)
            .accessToken(null)
            .refreshToken(null)
            .build();
  }
  
  public static <T> ApiResponse<T> successResponse(ApiResponseEnum responseEnum , T data) {
    return ApiResponse.<T>builder()
            .resultCode(responseEnum.getCode())
            .resultMsg(responseEnum.getMessage())
            .data(data)
            .accessToken(null)
            .refreshToken(null)
            .build();
  }
  
  public static <T> ApiResponse<T> successResponse(ApiResponseEnum responseEnum , T data , String jwtToken,String refreshToken) {
    return ApiResponse.<T>builder()
            .resultCode(responseEnum.getCode())
            .resultMsg(responseEnum.getMessage())
            .data(data)
            .accessToken(null)
            .refreshToken(null)
            .build();
  }
  
  
}
