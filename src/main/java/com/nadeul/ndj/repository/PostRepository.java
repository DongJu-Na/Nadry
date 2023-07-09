package com.nadeul.ndj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
}
