package com.nadeul.ndj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.PostDto;
import com.nadeul.ndj.entity.Post;
import com.nadeul.ndj.service.PostService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "PostService", description = "게시글 서비스 API")
@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class PostController<T> {
	
	private final PostService<T> postService;
	
	
	@GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Post>>> list() {
        return ResponseEntity.ok(postService.list());
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }
	
	/*
	@PostMapping("/")
    public ResponseEntity<ApiResponse<Post>> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> updatePost(@PathVariable("id") Integer id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable("id") Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok(ApiResponse.successResponse(ApiResponseEnum.SUCCESS, null, null, null));
    }
    */
    
    
	
}
