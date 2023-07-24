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
	/** exif.js 메타데이터 값들 보고 엔티티 , 컬럼값들도 변경 필요함   
	* 
	**/
	@Schema(description = "tourAPI 위도")
	private Float posX;
	@Schema(description = "tourAPI 경도")
	private Float posY;
	
}
