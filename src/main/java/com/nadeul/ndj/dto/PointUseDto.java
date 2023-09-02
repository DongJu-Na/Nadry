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
public class PointUseDto {
	@Schema(description = "상품번호" , example = "1")
	private Integer pdId;
	
}
