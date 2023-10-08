package com.nadeul.ndj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.StampDto;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.entity.Stamp;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.MemberRepository;
import com.nadeul.ndj.repository.StampRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StampService<T> {
  private final MemberRepository memberRepository;
  private final StampRepository stampRepository;
  
  public ApiResponse<List<StampDto>> myStampList() {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Object principal = authentication.getPrincipal();
      String email = ((UserDetails) principal).getUsername();
      
      Optional<Member> optionalMember = memberRepository.findByEmail(email);
      if (!optionalMember.isPresent()) {
           //사용자 정보를 찾을 수 없을 때 에러 반환
          return ApiResponse.errorResponse(ApiResponseEnum.UNKNOWN_MEMBER);
      }
      	List<Stamp> data = stampRepository.findByMemberMemId(optionalMember.get().getMemId());
      	List<StampDto> result = data.stream().map(rowData->{
      		return new StampDto(rowData); 
      	}).toList();
	  //List<StampDto> data = stampRepository.customFindByMemId(optionalMember.get().getMemId().toString());
	  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,result,null,null);
  }
  
  
  public ApiResponse<Stamp> createStamp(Stamp stamp) {
	  Stamp createdStamp = stampRepository.save(stamp);
	  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, createdStamp, null, null);
  }
  
  
  
    
}
