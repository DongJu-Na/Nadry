package com.nadeul.ndj.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "찜 DTO")
public class DibsDto {
	
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
    	@Schema(description = "찜 번호" , example = "1")
        private Integer diId;
    	@Schema(description = "회원번호" , example = "1")
        private Integer memId;
    	@Schema(description = "찜 일자" , example = "2023-08-22")
        private String dibsDate;
    	@Schema(description = "관광지 ID" , example = "1001")
        private String contentId;
    	@Schema(description = "관광지 명" , example = "불국사")
        private String contentName;
    	@Schema(description = "관광지 이미지 URL Path" , example = "공공API 이미지 URL")
        private String contentImageUrl;

    }


    
}