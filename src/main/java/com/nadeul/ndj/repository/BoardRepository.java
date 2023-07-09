package com.nadeul.ndj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nadeul.ndj.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

   @Query("SELECT b FROM Board b WHERE b.delYn = 'N' ORDER BY b.boId ASC")
   List<Board> findAllByDelYnOrderByBoIdAsc();
   
}
