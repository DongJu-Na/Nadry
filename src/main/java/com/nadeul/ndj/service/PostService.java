package com.nadeul.ndj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.entity.Post;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService<T> {
	
	 private final PostRepository postRepository;
	  
	  public ApiResponse<List<Post>> list() {
		  List<Post> data = postRepository.findAll();
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, data, null, null);
	  }
	  
	  public ApiResponse<Post> getPostById(Integer id) {
		  Optional<Post> postOptional = postRepository.findById(id);
		  if (postOptional.isPresent()) {
			  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, postOptional.get(), null, null);
		  }
		  return ApiResponse.failResponse(ApiResponseEnum.NOT_FOUND, null);
	  }
	  
	  public ApiResponse<Post> createPost(Post post) {
		  Post createdPost = postRepository.save(post);
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, createdPost, null, null);
	  }
	  
	  public ApiResponse<Post> updatePost(Integer id, Post post) {
		  Optional<Post> postOptional = postRepository.findById(id);
		  if (postOptional.isPresent()) {
			  Post existingPost = postOptional.get();
			  // Update the existing post with the new values
			  existingPost.setTitle(post.getTitle());
			  existingPost.setContent(post.getContent());
			  // Set other fields as needed
			  
			  Post updatedPost = postRepository.save(existingPost);
			  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, updatedPost, null, null);
		  }
		  return ApiResponse.failResponse(ApiResponseEnum.NOT_FOUND, null);
	  }
	  
	  public void deletePost(Integer id) {
		  postRepository.deleteById(id);
	  }
}
