package com.nadeul.ndj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nadeul.ndj.dto.StampDto;
import com.nadeul.ndj.entity.Stamp;

public interface StampRepository extends JpaRepository<Stamp, Integer> {

//	@Query("SELECT s FROM Stamp s LEFT JOIN s.stampMaster sm WHERE s.id = :memId")
//    List<StampDto> customFindByMemId(@Param("memId") String memId);
	
	 List<Stamp> findByMemberMemId(Integer memId);

}
