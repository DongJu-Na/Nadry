package com.nadeul.ndj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.entity.Board;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService<T> {
	
  private final BoardRepository boardRepository;
  
  public ApiResponse<List<Board>> list() {
	  List<Board> data = boardRepository.findAllByDelYnOrderByBoIdAsc();
	  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,data,null,null);
  }
  
  public ApiResponse<Board> getBoardById(Integer id) {
	  Optional<Board> boardOptional = boardRepository.findById(id);
	  if (boardOptional.isPresent()) {
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, boardOptional.get(), null, null);
	  }
	  return ApiResponse.failResponse(ApiResponseEnum.NOT_FOUND, "");
  }
  
  public ApiResponse<Board> createBoard(Board board) {
	  Board createdBoard = boardRepository.save(board);
	  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, createdBoard, null, null);
  }
  
  public ApiResponse<Board> updateBoard(Integer id, Board board) {
	  Optional<Board> boardOptional = boardRepository.findById(id);
	  if (boardOptional.isPresent()) {
		  Board existingBoard = boardOptional.get();
		  existingBoard.setBoardName(board.getBoardName());
		  existingBoard.setManagerYn(board.getManagerYn());
		  
		  Board updatedBoard = boardRepository.save(existingBoard);
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, updatedBoard, null, null);
	  }
	  return ApiResponse.failResponse(ApiResponseEnum.NOT_FOUND, null);
  }
  
  public void deleteBoard(Integer id) {
	  boardRepository.deleteById(id);
  }
  
    
}
