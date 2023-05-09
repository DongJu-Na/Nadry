package com.nadeul.ndj.repository;

import com.nadeul.ndj.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
