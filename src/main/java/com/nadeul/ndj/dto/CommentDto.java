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
    	
        @Schema(description = "댓글 번호" , example = "1")
		private Integer cmId;
        @Schema(description = "게시판 번호" , example = "1")
		private Integer boId;
        @Schema(description = "게시글 번호" , example = "1")
		private Integer ptId;
        @Schema(description = "내용" , example = "반갑습니다.")
		private String content;
        @Schema(description = "좋아요" , example = "1")
		private int likes;
        @Schema(description = "삭제여부" , example = "N")
		private String delYn;
        @Schema(description = "작성일자" , example = "YYYY-MM-DD HH:SS")
		private Date createDate = new Date();
        @Schema(description = "작성자" , example = "홍길동")
		private String createBy;
    	  
    }

    @RequiredArgsConstructor
    @Getter
    public static class Response {
    	@Schema(description = "댓글 번호" , example = "1")
		private Integer cmId;
		@Schema(description = "게시판 번호" , example = "1")
		private Integer boId;
		@Schema(description = "게시글 번호" , example = "1")
		private Integer ptId;
		@Schema(description = "내용" , example = "반갑습니다.")
		private String content;
		@Schema(description = "좋아요" , example = "1")
		private int likes;
		@Schema(description = "삭제여부" , example = "N")
		private String delYn;
		@Schema(description = "수정일자" , example = "YYYY-MM-DD HH:SS")
		private Date updateDate = new Date();
		@Schema(description = "수정자" , example = "홍길동")
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