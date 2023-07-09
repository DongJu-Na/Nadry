package com.nadeul.ndj.dto;

import java.util.Date;

import com.nadeul.ndj.entity.Comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Schema(description = "댓글 DTO")
public class CommentDto {
	
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
    	
		private Integer cmId;
		private Integer boId;
		private Integer ptId;
		private String content;
		private int likes;
		private String delYn;
		private Date createDate = new Date();
		private String createBy;
    	  
    }

    @RequiredArgsConstructor
    @Getter
    public static class Response {
		private Integer cmId;
		private Integer boId;
		private Integer ptId;
		private String content;
		private int likes;
		private String delYn;
		private Date updateDate = new Date();
		private String updateBy;
		
        public Response(Comment comment) {
            this.cmId = comment.getCmId();
            this.boId = comment.getBoard().getBoId();
            this.ptId = comment.getPost().getPtId();
            this.content = comment.getContent();
            this.likes = comment.getLikes();
            this.delYn = comment.getDelYn();
            this.updateDate = comment.getUpdateDate();
            this.updateBy = comment.getUpdateBy();
        }
    }
    
}