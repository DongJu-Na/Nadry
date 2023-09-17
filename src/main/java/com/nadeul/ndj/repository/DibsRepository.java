package com.nadeul.ndj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import com.nadeul.ndj.entity.Dibs;
import com.nadeul.ndj.entity.Member;

public interface DibsRepository extends JpaRepository<Dibs, Integer> {
	
	Optional<Dibs> findByMemberAndContentId(Member member,String contentId);
    
    List<Dibs> findByMemberOrderByDibsDateDesc(Member member , Pageable pageable);
    
    
    
    
}
