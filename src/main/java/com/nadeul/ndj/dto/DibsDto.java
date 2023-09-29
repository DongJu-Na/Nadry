package com.nadeul.ndj.dto;
import java.time.LocalDateTime;

import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.model.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "찜 DTO")
public class DibsDto {
	
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
    	@Schema(description = "관광지 ID" , example = "1001")
        private String contentId;
    	
    	@Schema(description = "관광지 타입 ID" , example = "17")
        private String contentTypeId;
    	
    	@Schema(description = "관광지 명" , example = "불국사")
        private String contentName;
    	
    	@Schema(description = "관광지 이미지 URL Path" , example = "공공API 이미지 URL")
        private String contentImageUrl;
    	

    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class contentCheckRequest {
    	@Schema(description = "콘텐츠 아이디" , example = "1")
    	private String contentId;
    }
    
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
    	  private Integer diId;
    	  private LocalDateTime dibsDate;
    	  private String contentId;
    	  private String contentTypeId;
    	  private String contentName;
    	  private String contentImageUrl;
    	  
    }


    
}