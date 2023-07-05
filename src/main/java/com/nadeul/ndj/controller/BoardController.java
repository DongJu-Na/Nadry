package com.nadeul.ndj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.PostDto;
import com.nadeul.ndj.service.BoardService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "BoardService", description = "게시판 서비스 API")
@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
		/*
    @PostMapping("/posts")
    public ResponseEntity save(@RequestBody PostDto.Request dto) {
        return ResponseEntity.ok(boardService.save(dto));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.findById(id));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostDto.Request dto) {
        boardService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok(id);
    }
    */
    
	
}
