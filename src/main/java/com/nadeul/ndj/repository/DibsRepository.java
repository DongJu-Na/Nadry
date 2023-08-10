package com.nadeul.ndj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import com.nadeul.ndj.entity.Dibs;
import com.nadeul.ndj.entity.Member;

public interface DibsRepository extends JpaRepository<Dibs, Integer> {
	
    boolean findByMemberAndContentId(Member member,String contentId);
    
    List<Dibs> findByMemberOrderByDibsDateDesc(Member member , Pageable pageable);
    
    
    
    
}
