package com.nadeul.ndj.dto;

import java.util.Date;

import com.nadeul.ndj.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class CommentDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
    	
		private Integer cmId;
		private Integer bdId;
		private Integer ptId;
		private String content;
		private int likes;
		private String delYn;
		private Date createDate = new Date();
		private String createBy;
    	  
        /* Dto -> Entity */
        public Comment toEntity() {
            Comment comments = Comment.builder()
                    .bdId(bdId)
                    .ptId(ptId)
                    .content(content)
                    .likes(likes)
                    .createDate(createDate)
                    .createBy(createBy)
                    .build();
            return comments;
        }
    }

    @RequiredArgsConstructor
    @Getter
    public static class Response {
		private Integer cmId;
		private Integer bdId;
		private Integer ptId;
		private String content;
		private int likes;
		private String delYn;
		private Date updateDate = new Date();
		private String updateBy;
		
        /* Entity -> Dto*/
        public Response(Comment comment) {
            this.cmId = comment.getCmId();
            this.bdId = comment.getBdId();
            this.ptId = comment.getPtId();
            this.content = comment.getContent();
            this.likes = comment.getLikes();
            this.delYn = comment.getDelYn();
            this.updateDate = comment.getUpdateDate();
            this.updateBy = comment.getUpdateBy();
        }
    }
}