package com.nadeul.ndj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.service.BoardService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "BoardService", description = "게시판 서비스 API")
@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	
	
}
