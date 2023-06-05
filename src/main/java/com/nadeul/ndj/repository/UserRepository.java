package com.nadeul.ndj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.Member;

public interface UserRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByEmail(String email);
}
