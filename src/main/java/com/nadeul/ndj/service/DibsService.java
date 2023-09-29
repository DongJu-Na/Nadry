package com.nadeul.ndj.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.DibsDto;
import com.nadeul.ndj.dto.DibsDto.Response;
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
	
  public ApiResponse<List<Dibs>> list(Pageable pageable) {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  Object principal = authentication.getPrincipal();
	  String email = ((UserDetails) principal).getUsername();
	  Optional<Member>  optionalMember = memberRepository.findByEmail(email);
  	
	  if (!optionalMember.isPresent()) {
            // 사용자 정보를 찾을 수 없을 때 에러 반환
            return ApiResponse.errorResponse(ApiResponseEnum.UNKNOWN_MEMBER);
        }
	    
	  Page<Dibs> data = dibsRepository.findByMemberOrderByDibsDateDesc(optionalMember.get(),pageable);
	  List<Dibs> list = data.getContent();
	  
	return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,list,null,null);
  }
  
  public ApiResponse<Response> contentDibsCheck(DibsDto.contentCheckRequest request) {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  Object principal = authentication.getPrincipal();
	  String email = ((UserDetails) principal).getUsername();
	  Optional<Member>  optionalMember = memberRepository.findByEmail(email);
  	
	  if (!optionalMember.isPresent()) {
            // 사용자 정보를 찾을 수 없을 때 에러 반환
            return ApiResponse.errorResponse(ApiResponseEnum.UNKNOWN_MEMBER);
        }
	    
	  Optional<Dibs> data = dibsRepository.findByMemberAndContentId(optionalMember.get(),request.getContentId());
	  
	  if(data.isPresent()) {
		  DibsDto.Response dibsDto = DibsDto.Response.builder()
                  .diId(data.get().getDiId())
                  .dibsDate(data.get().getDibsDate())
                  .contentId(data.get().getContentId())
                  .contentName(data.get().getContentName())
                  .contentImageUrl(data.get().getContentImageUrl())
                  .build();
 
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,dibsDto,null,null);
	  }else {
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,null,null,null);
	  }
	  
	
  }
	
	public ApiResponse<T> dibs(DibsDto.Request request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			Optional<Member>  optionalMember = memberRepository.findByEmail(email);
				if (optionalMember.isPresent()) {
					
					Optional<Dibs> alreadyExists = dibsRepository.findByMemberAndContentId(optionalMember.get(), request.getContentId());

			        if (alreadyExists.isPresent()) {
			        	dibsRepository.delete(alreadyExists .get());
			        }else {
			        	Dibs dibs = Dibs.builder()
		        			       .member(optionalMember.get())
		        			       .dibsDate(LocalDateTime.now())
		        			       .contentId(request.getContentId())
		        			       .contentTypeId(request.getContentTypeId())
		        			       .contentName(request.getContentName())
		        			       .contentImageUrl(request.getContentImageUrl())
		        				   .build();
		        		dibsRepository.save(dibs);
			        }
			        
				} else {
					return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
				}
		} else {
			return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
		}
		
		
		return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,null,null,null);
	}
	

}
