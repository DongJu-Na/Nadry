package com.nadeul.ndj.dto;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.nadeul.ndj.entity.Post;

public class PostDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
    	 private Integer ptId;
    	  private Integer boId;
    	  private String title;
    	  private String content;
    	  private int likes;
    	  private int view;
    	  private String delYn;
    	  private Date createDate;
    	  private String createBy;
    	  private Date updateDate;
    	  private String updateBy;
    	  private String contentId;

        /* Dto -> Entity */
        public Post toEntity() {
        	Post posts = Post.builder()
                    .ptId(ptId)
                    .boId(boId)
                    .title(title)
                    .content(content)
                    .likes(likes)
                    .view(view)
                    .delYn(delYn)
                    .createDate(createDate)
                    .createBy(createBy)
                    .updateDate(updateDate)
                    .updateBy(updateBy)
                    .contentId(contentId)
                    .build();

            return posts;
        }
    }

    @Getter
    public static class Response {
    	  private Integer ptId;
    	  private Integer boId;
    	  private String title;
    	  private String content;
    	  private int likes;
    	  private int view;
    	  private String delYn;
    	  private Date createDate;
    	  private String createBy;
    	  private Date updateDate;
    	  private String updateBy;
    	  private String contentId;
    	  
    	  //private final List<CommentDto.Response> comments;

        public Response(Post post) {
            this.ptId = post.getPtId();
            this.boId = post.getBoId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.likes = post.getLikes();
            this.view = post.getView();
            this.createDate = post.getCreateDate();
            this.createBy = post.getCreateBy();
            this.updateDate = post.getUpdateDate();
            this.updateBy = post.getUpdateBy();
            this.contentId = post.getContentId();
            //this.comments = post.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
        }
    }
}