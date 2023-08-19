package com.nadeul.ndj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.entity.Board;
import com.nadeul.ndj.enums.ApiResponseEnum;
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
	
	@GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Board>> getBoardById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }
	
	@PostMapping("/")
    public ResponseEntity<ApiResponse<Board>> createBoard(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.createBoard(board));
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Board>> updateBoard(@PathVariable("id") Integer id, @RequestBody Board board) {
        return ResponseEntity.ok(boardService.updateBoard(id, board));
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBoard(@PathVariable("id") Integer id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok(ApiResponse.successResponse(ApiResponseEnum.SUCCESS, null, null, null));
    }
    
	
}
