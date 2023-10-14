package com.nadeul.ndj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.entity.Orders;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.MemberRepository;
import com.nadeul.ndj.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService<T> {
  private final MemberRepository memberRepository;
  private final OrdersRepository ordersRepository;
  
  @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
  public ApiResponse<List<Map<String, Object>>> myOrderList() {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Object principal = authentication.getPrincipal();
      String email = ((UserDetails) principal).getUsername();
      
      Optional<Member> optionalMember = memberRepository.findByEmail(email);
      if (!optionalMember.isPresent()) {
           //사용자 정보를 찾을 수 없을 때 에러 반환
          return ApiResponse.errorResponse(ApiResponseEnum.UNKNOWN_MEMBER);
      }
      
      	List<Orders> data = ordersRepository.findByMemberMemId(optionalMember.get().getMemId());
      	List<Map<String, Object>> orderDataList = data.stream()
      		    .map(order -> {
      		        Map<String, Object> orderData = new HashMap<>();
      		        orderData.put("odId", order.getOdId());
      		        orderData.put("status", order.getStatus());
      		        orderData.put("pdId", order.getCart().getIncludedProducts().get(0).getProduct().getPdId());
      		        orderData.put("name", order.getCart().getIncludedProducts().get(0).getProduct().getName());
      		        orderData.put("thumbnailUrl", order.getCart().getIncludedProducts().get(0).getProduct().getThumbnailUrl());
      		        orderData.put("orderDate", order.getOrderDate());
      		        //orderData.put("cart", order.getCart());
      		        //orderData.put("member", order.getMember());
      		        return orderData;
      		    })
      		    .sorted((order1, order2) -> {
                  // 내림차순 정렬
                  Integer odId1 = (Integer) order1.get("odId");
                  Integer odId2 = (Integer) order2.get("odId");
                  return odId2.compareTo(odId1);
      		  	})
      		    .collect(Collectors.toList());
      	
      	
	  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,orderDataList,null,null);
  }
  

    
}
