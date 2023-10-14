package com.nadeul.ndj.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.PointEarnDto;
import com.nadeul.ndj.dto.PointHistoryDto;
import com.nadeul.ndj.dto.PointUseDto;
import com.nadeul.ndj.entity.Cart;
import com.nadeul.ndj.entity.CartProduct;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.entity.Orders;
import com.nadeul.ndj.entity.Point;
import com.nadeul.ndj.entity.PointHistory;
import com.nadeul.ndj.entity.Product;
import com.nadeul.ndj.entity.Trip;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.enums.OrderStatus;
import com.nadeul.ndj.repository.CartProductRepository;
import com.nadeul.ndj.repository.CartRepository;
import com.nadeul.ndj.repository.MemberRepository;
import com.nadeul.ndj.repository.OrdersRepository;
import com.nadeul.ndj.repository.PointHistoryRepository;
import com.nadeul.ndj.repository.PointRepository;
import com.nadeul.ndj.repository.ProductRepository;
import com.nadeul.ndj.repository.TripRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointService<T> {
	private final TripRepository tripRepository;
	private final PointRepository pointRepository;
	private final PointHistoryRepository pointHistoryRepository;
	private final ProductRepository productRepository; 
	private final MemberRepository memberRepository;
	private final CartRepository cartRepository;
	private final OrdersRepository ordersRepository;
	private final CartProductRepository cartProductRepository;
	
	public ApiResponse<List<PointHistoryDto>> myPointHistoryList(Pageable pageable) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		
		if(principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			Optional<Member>  optionalMember = memberRepository.findByEmail(email);
			
			Page<PointHistory> pointHistorys = pointHistoryRepository.findByMemberMemId(optionalMember.get().getMemId(),pageable);
			List<PointHistoryDto> pointHistoryList =  pointHistorys.getContent().stream().map(rowData ->{
			    return new PointHistoryDto(rowData);
			} ).collect(Collectors.toList());
			  
			  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, pointHistoryList, null, null);
			  
		}else {
		    // principal이 UserDetails 타입이 아닌 경우 처리 로직 -> 응답 객체를 null 로 반환
			return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
		}
	 }
	
	@Transactional
	public ApiResponse<T> earn(PointEarnDto dto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		 
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			Optional<Member>  optionalMember = memberRepository.findByEmail(email);
				if (optionalMember.isPresent()) {
			    // Member 엔티티를 사용하여 필요한 작업을 수행합니다.
					// process 0 거리가 유효거리 1km안으로 들어왔는지 체크
						if(distance(dto.getRealPosX(), dto.getRealPosY(), dto.getPosX(), dto.getPosY(),"kilometer") < 1.0) {
							return ApiResponse.failResponse(ApiResponseEnum.POSITION_UNAVAILABLE, ""); 
						}
				    
				    // process 1 여행 이력 Insert
						var trip = Trip.builder()
									.member(optionalMember.get())
									.posX(dto.getPosX())
									.posY(dto.getPosY())
									.realPosX(dto.getRealPosX())
									.realPosY(dto.getRealPosY())
									.tripDate(dto.getTripDate())
									.contentId(dto.getContentId())
									.build();
						
						   tripRepository.save(trip);
						// process 2 최종 포인트 merge 
						var pointEt =  pointRepository.findByMemberMemId(optionalMember.get().getMemId());
						 
						var point = pointEt.map(p -> {
					    p.setPoint(p.getPoint() + 10);
					    return p;
						}).orElseGet(() -> {
						    var newPoint = Point.builder()
						            .point(10)
						            .member(optionalMember.get())
						            .blackYn("N")
						            .build();
						    return newPoint;
						});
						
						var savePoint = pointRepository.save(point);
						// process 3 포인트 히스토리 Insert
						var pointHistory = PointHistory.builder()
								.point(savePoint)
								.usePoint(10)
								.usedBy(optionalMember.get().getEmail())
								.useDate(LocalDateTime.now())
								.build();
						
						pointHistoryRepository.save(pointHistory);
				} else {
				    // Member가 존재하지 않는 경우 처리 로직을 작성합니다.
						return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
				}
		} else {
		    // principal이 UserDetails 타입이 아닌 경우 처리 로직 -> 응답 객체를 null 로 반환
			return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
		}
		
		
    return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,null,null,null);
  }
	
	@Transactional
	public ApiResponse<T> use(PointUseDto dto) {
        // 1. 사용자 인증 및 권한 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserDetails)) {
            // 인증 실패 시 에러 반환
            return ApiResponse.errorResponse(ApiResponseEnum.UNKNOWN_MEMBER);
        }

        String email = ((UserDetails) principal).getUsername();
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (!optionalMember.isPresent()) {
            // 사용자 정보를 찾을 수 없을 때 에러 반환
            return ApiResponse.errorResponse(ApiResponseEnum.UNKNOWN_MEMBER);
        }

        Member member = optionalMember.get();
        Optional<Product> productOptional = productRepository.findById(dto.getPdId());

        // 2. 입력 데이터 검사
        Integer usePoints = productOptional.get().getPoint();
        if (usePoints == null || usePoints <= 0) {
            // 유효하지 않은 포인트 사용량일 때 에러 반환
            return ApiResponse.errorResponse(ApiResponseEnum.UNKNOWN_POINT);
        }

        // 3. 포인트 사용 가능 여부 확인
        Optional<Point> pointOptional = pointRepository.findByMemberMemId(member.getMemId());
        if (!pointOptional.isPresent()) {
            // 포인트 정보를 찾을 수 없을 때 에러 반환
            return ApiResponse.errorResponse(ApiResponseEnum.NOTFOUND_POINT);
        }

        Point point = pointOptional.get();
        Integer availablePoints = point.getPoint();

        if (usePoints > availablePoints) {
            // 포인트가 부족할 때 에러 반환
            return ApiResponse.errorResponse(ApiResponseEnum.LACK_POINT);
        }
        
        // 4. 포인트 사용 로직
        // 여기서 카트 테이블에 상품을 담고, 주문 내역에 카트를 옮기는 등의 작업을 수행할 수 있습니다.
        // 해당 로직은 프로젝트의 요구사항에 따라 구현해야 합니다.
        
        
        
        Cart cart = new Cart();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(productOptional.get()); // product는 제품 엔티티의 인스턴스
        cartProduct.setCount(1); // 제품의 수량 설정 등
        cartProduct.setCart(cart);
        cartProduct.setProduct(productOptional.get());
        
        cart.getIncludedProducts().add(cartProduct);
        cart.setMember(member);
        
        Orders orders = new Orders();
        	   orders.setStatus(OrderStatus.CREATED);
        	   orders.setCart(cart);
        	   orders.setMember(member);
        
        cartRepository.save(cart);
        cartProductRepository.save(cartProduct);
        ordersRepository.save(orders);

        // 5. 포인트 차감 및 업데이트
        point.setPoint(availablePoints - usePoints);
        pointRepository.save(point);

        // 6. 포인트 히스토리에 사용 내역 저장
        PointHistory pointHistory = new PointHistory();
        pointHistory.setPoint(point);
        pointHistory.setUseDate(LocalDateTime.now());
        pointHistory.setUsePoint(usePoints);
        pointHistory.setUsedBy(member.getEmail());
        pointHistory.setRemarks("상품 구매");
        pointHistory.setMember(member);
        pointHistoryRepository.save(pointHistory);

        // 성공적으로 포인트 사용이 완료되었을 때 성공 응답 반환
        return ApiResponse.successResponse(ApiResponseEnum.POINT_USE_SUCCESS, null , null, null);
  }

	
	

	/**
	 * 두 지점간의 거리 계산
	 * 
	 * @param lat1 지점 1 위도
	 * @param lon1 지점 1 경도 
	 * @param lat2 지점 2 위도
	 * @param lon2 지점 2 경도
	 * @param unit 거리 표출단위 
	 * @return
	 */
	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		
		if (unit == "kilometer") {
			dist = dist * 1.609344;
		} else if(unit == "meter"){
			dist = dist * 1609.344;
		} 

		return (dist);
	}
	

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	// This function converts radians to decimal degrees
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
  
  
}


