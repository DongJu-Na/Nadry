package com.nadeul.ndj.dto;


import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "포인트 적립 DTO")
public class PointEarnDto {
	/** exif.js 메타데이터 값들 보고 엔티티 , 컬럼값들도 변경 필요함   
	* 
	**/
	
	@Schema(description = "컨텐츠ID")
	private String contentId;
	
	@Schema(description = "tourAPI 위도")
	private Float posX;
	
	@Schema(description = "tourAPI 경도")
	private Float posY;
	
	@Schema(description = "실제 위도")
	private Float realPosX;
	
	@Schema(description = "실제 경도")
	private Float realPosY;
	
	@Schema(description = "여행 일자 yyyy-mm-dd")
	private Date tripDate;
	
	
}
