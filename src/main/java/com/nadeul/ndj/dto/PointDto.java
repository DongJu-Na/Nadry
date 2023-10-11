package com.nadeul.ndj.dto;


import com.nadeul.ndj.entity.Point;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointDto {
	@Schema(description = "포인트 번호" , example = "1")
    private Integer poId;
	@Schema(description = "포인트" , example = "100")
	private Integer point;
	@Schema(description = "블랙여부" , example = "Y")
	private String blackYn;
    
    public PointDto(Point p) {
    	this.poId = p.getPoId();
    	this.point = p.getPoint();
    	this.blackYn = p.getBlackYn();
    }
	
}
