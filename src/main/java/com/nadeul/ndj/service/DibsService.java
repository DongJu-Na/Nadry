package com.nadeul.ndj.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.DibsDto;
import com.nadeul.ndj.entity.Dibs;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.DibsRepository;
import com.nadeul.ndj.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DibsService<T> {
	
	private final DibsRepository dibsRepository;
	private final MemberRepository memberRepository;
	
  public ApiResponse<List<Dibs>> list(DibsDto.ListRequest request) {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  Object principal = authentication.getPrincipal();
	  List<Dibs> data = null;
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			Optional<Member>  optionalMember = memberRepository.findByEmail(email);
				if (optionalMember.isPresent()) {
					 Pageable pageable = PageRequest.of(request.getPageNo(),request.getListCnt());
					   
					data = dibsRepository.findByMemberOrderByDibsDateDesc(optionalMember.get(),pageable);
				} else {
					return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
				}
		} else {
			return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
		}
		
	  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,data,null,null);
  }
	
	public ApiResponse<T> addDibs(DibsDto.Request request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			Optional<Member>  optionalMember = memberRepository.findByEmail(email);
				if (optionalMember.isPresent()) {
					
					boolean alreadyExists = dibsRepository.findByMemberAndContentId(optionalMember.get(), request.getContentId());

			        if (!alreadyExists) {
			        	try {
			        		var dibs = Dibs.builder()
			        			       .member(optionalMember.get())
			        			       .contentId(request.getContentId())
			        			       .dibsDate(LocalDateTime.now())
			        			       .contentName(request.getContentName())
			        			       .contentImageUrl(request.getContentImageUrl())
			        				   .build();
			        		dibsRepository.save(dibs);
						} catch (Exception e) {
							return ApiResponse.failResponse(ApiResponseEnum.INTERNAL_SERVER_ERROR, "");
						}
			        	
			        }
			        
				} else {
					return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
				}
		} else {
		    // principal이 UserDetails 타입이 아닌 경우 처리 로직 -> 응답 객체를 null 로 반환
			return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
		}
		
		
		return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,null,null,null);
	}
	
	
	public ApiResponse<T> removeDibs(DibsDto.Request request) {
	    dibsRepository.deleteById(request.getDiId());
		return  ApiResponse.successResponse(ApiResponseEnum.SUCCESS,null,null,null);
	}

}
