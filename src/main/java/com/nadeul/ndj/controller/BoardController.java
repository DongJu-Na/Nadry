package com.nadeul.ndj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.entity.Board;
import com.nadeul.ndj.service.BoardService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "BoardService", description = "게시판 서비스 API")
@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController<T> {
	
	private final BoardService<T> boardService;
	
	@GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Board>>> list() {
        return ResponseEntity.ok(boardService.list());
    }
	

    
    
	
}
