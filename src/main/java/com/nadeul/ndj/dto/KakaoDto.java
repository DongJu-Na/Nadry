package com.nadeul.ndj.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoDto {

	@Schema(description = "요청 시 전달한 state 값과 동일한 값" , example = "")
	private String code;
	
	@Schema(description = "토큰 받기 요청에 필요한 인가 코드" , example = "")
	private String state;
	
}




