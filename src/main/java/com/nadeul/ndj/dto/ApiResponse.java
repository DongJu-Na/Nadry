package com.nadeul.ndj.dto;

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
  private String Token;
  
}
