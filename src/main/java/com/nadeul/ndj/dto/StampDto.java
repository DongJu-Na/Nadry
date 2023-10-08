package com.nadeul.ndj.dto;


import java.time.LocalDateTime;
import java.util.Date;

import com.nadeul.ndj.entity.Stamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class StampDto {

	@Schema(description = "스탬프 번호" , example = "1")
	private Integer stId;
		
	@Schema(description = "스탬프 마스터 번호" , example = "1")
	private Integer stmId;
	
	@Schema(description = "스탬프 수집일자" , example = "2023-09-11 00:07:39.302")
	private LocalDateTime stampCollectDate;
	
	@Schema(description = "스탬프 이름" , example = "서울")
	private String stampName;
	
	@Schema(description = "스탬프 발행일자" , example = "2023-09-11 00:07:39.302")
	private LocalDateTime stampCreateDate; 
	
	@Schema(description = "스탬프 이미지 url" , example = "")
	private String stampImageUrl;
	
	@Schema(description = "스탬프 구분 코드" , example = "")
	private String stampType;
	
    public StampDto(Stamp stamp) {
	this.stId = stamp.getStId();
	this.stmId = stamp.getStampMaster().getStmId();
	this.stampCollectDate = stamp.getStampCollectDate();
	this.stampName = stamp.getStampMaster().getStampName();
	this.stampCreateDate = stamp.getStampMaster().getStampCreateDate();
	this.stampImageUrl = stamp.getStampMaster().getStampImageUrl();
	this.stampType = stamp.getStampMaster().getStampType();
	}
}




