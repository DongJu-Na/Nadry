package com.nadeul.ndj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.Stamp;

public interface StampRepository extends JpaRepository<Stamp, Integer> {

//	@Query("SELECT s FROM Stamp s LEFT JOIN s.stampMaster sm WHERE s.id = :memId")
//    List<StampDto> customFindByMemId(@Param("memId") String memId);
	
	 List<Stamp> findByMemberMemId(Integer memId);
	 
	 Optional<Stamp> findByMemberMemIdAndStampMasterStmId(Integer memId, Integer stmId);

}
