package com.nadeul.ndj.dto;
import lombok.*;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.nadeul.ndj.entity.Post;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "게시글 DTO")
public class PostDto {
	
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
    	  @Schema(description = "게시글 번호" , example = "1")
    	  private Integer ptId;
    	  @Schema(description = "게시판 번호" , example = "1")
    	  private Integer boId;
    	  @Schema(description = "제목" , example = "게시글 제목")
    	  private String title;
    	  @Schema(description = "내용" , example = "게시글 내용")
    	  private String content;
    	  @Schema(description = "좋아요" , example = "1")
    	  private int likes;
    	  @Schema(description = "조회수" , example = "1")
    	  private int view;
    	  @Schema(description = "삭제여부" , example = "N")
    	  private String delYn;
    	  @Schema(description = "생성일자" , example = "YYYY-MM-DD HH:SS")
    	  private Date createDate;
    	  @Schema(description = "생성자" , example = "홍길동")
    	  private String createBy;
    	  @Schema(description = "수정일자" , example = "YYYY-MM-DD HH:SS")
    	  private Date updateDate;
    	  @Schema(description = "수정자" , example = "홍길동")
    	  private String updateBy;
    	  @Schema(description = "콘텐츠아이디" , example = "공공API 데이터 매핑 키")
    	  private String contentId;

    }

    @Getter
    public static class Response {
    	  @Schema(description = "게시글 번호" , example = "1")
    	  private Integer ptId;
    	  @Schema(description = "게시판 번호" , example = "1")
    	  private Integer boId;
    	  @Schema(description = "제목" , example = "게시글 제목")
    	  private String title;
    	  @Schema(description = "내용" , example = "게시글 내용")
    	  private String content;
    	  @Schema(description = "좋아요" , example = "1")
    	  private int likes;
    	  @Schema(description = "조회수" , example = "1")
    	  private int view;
    	  @Schema(description = "삭제여부" , example = "N")
    	  private String delYn;
    	  @Schema(description = "생성일자" , example = "YYYY-MM-DD HH:SS")
    	  private Date createDate;
    	  @Schema(description = "생성자" , example = "홍길동")
    	  private String createBy;
    	  @Schema(description = "수정일자" , example = "YYYY-MM-DD HH:SS")
    	  private Date updateDate;
    	  @Schema(description = "수정자" , example = "홍길동")
    	  private String updateBy;
    	  @Schema(description = "콘텐츠아이디" , example = "공공API 데이터 매핑 키")
    	  private String contentId;
    	  private final List<CommentDto.Response> comments;

        public Response(Post post) {
            this.ptId = post.getPtId();
            this.boId = post.getBoard().getBoId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.likes = post.getLikes();
            this.view = post.getView();
            this.createDate = post.getCreateDate();
            this.createBy = post.getCreateBy();
            this.updateDate = post.getUpdateDate();
            this.updateBy = post.getUpdateBy();
            this.contentId = post.getContentId();
            this.comments = post.getComment().stream().map(CommentDto.Response::new).collect(Collectors.toList());
        }
    }
    
}