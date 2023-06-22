package com.nadeul.ndj.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointEarnDto {
	/** exif.js 메타데이터 값들 보고 엔티티 , 컬럼값들도 변경 필요함   
	* 
	**/
	private Float posX;
	private Float posY;
	
}
