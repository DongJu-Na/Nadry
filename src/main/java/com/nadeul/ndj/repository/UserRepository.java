package com.nadeul.ndj.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
