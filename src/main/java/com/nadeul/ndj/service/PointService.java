package com.nadeul.ndj.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.PointEarnDto;
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
	private static final int EARTH_RADIUS = 6371; // 지구의 반지름 (단위: km)
	private final TripRepository tripRepository;
	private final PointRepository pointRepository;
	private final PointHistoryRepository pointHistoryRepository;
	private final ProductRepository productRepository; 
	private final MemberRepository memberRepository;
	private final CartRepository cartRepository;
	private final OrdersRepository ordersRepository;
	private final CartProductRepository cartProductRepository;
	
	@Transactional
	public ApiResponse<T> earn(PointEarnDto dto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		 
		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			Optional<Member>  optionalMember = memberRepository.findByEmail(email);
				if (optionalMember.isPresent()) {
			    // Member 엔티티를 사용하여 필요한 작업을 수행합니다.
					// process 0 거리가 유효거리 50m안으로 들어왔는지 체크
						if(calculateDistance(dto.getRealPosX(), dto.getRealPosY(), dto.getPosX(), dto.getPosY()) < 50) {
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
						
						var saveTripHistory = tripRepository.save(trip);
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
								.poId(savePoint.getPoId())
								.usePoint(10)
								.usedBy(optionalMember.get().getEmail())
								.useDate(LocalDateTime.now())
								.build();
						
						var savePointHistory = pointHistoryRepository.save(pointHistory);
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

        // 2. 입력 데이터 검사
        Integer usePoints = member.getPoint().getPoint();
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
        
        Optional<Product> productOptional = productRepository.findById(dto.getPdId());
        
        Cart cart = new Cart();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(productOptional.get()); // product는 제품 엔티티의 인스턴스
        cartProduct.setCount(1); // 제품의 수량 설정 등
        
        cart.setIncludedProducts(null);
        cart.setMember(member);
        
        Orders orders = new Orders();
        	   orders.setStatus(OrderStatus.CREATED);
        	   orders.setCart(cart);
        	   orders.setMember(member);
        
        cartProductRepository.save(cartProduct);
        cartRepository.save(cart);
        ordersRepository.save(orders);

        // 5. 포인트 차감 및 업데이트
        point.setPoint(availablePoints - usePoints);
        pointRepository.save(point);

        // 6. 포인트 히스토리에 사용 내역 저장
        PointHistory pointHistory = new PointHistory();
        pointHistory.setPoId(point.getPoId());
        pointHistory.setUseDate(LocalDateTime.now());
        pointHistory.setUsePoint(usePoints);
        pointHistory.setUsedBy(member.getEmail());
        pointHistoryRepository.save(pointHistory);

        // 성공적으로 포인트 사용이 완료되었을 때 성공 응답 반환
        return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, null , null, null);
  }

	
	

	/**
	 * @param lat1 첫 번째 지점의 위도 x
	 * @param lon1 첫 번째 지점의 경도 y
	 * @param lat2 두 번째 지점의 위도 x
	 * @param lon2 두 번째 지점의 경도 y
	 * @return 두 지점 사이의 거리 (미터 단위)
	 * 
	 * 1은 출발점
	 * 2는 도착점
	 * 위도(latitude)와 경도(longitude)를 기반으로 두 지점 간의 거리를 계산합니다.
	 * lat1과 lon1은 첫 번째 지점의 위도와 경도이고, lat2와 lon2는 두 번째 지점의 위도와 경도입니다.
	 * 거리는 미터 단위로 반환됩니다.
	 */
  public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
  	double distance = 0;
  	try {
  		double dLat = Math.toRadians(lat2 - lat1);
      double dLon = Math.toRadians(lon2 - lon1);
      double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
          Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
          Math.sin(dLon / 2) * Math.sin(dLon / 2);
      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      distance = EARTH_RADIUS * c * 1000.0; // km를 m로 변환
  	}catch (Exception e) {
  		e.printStackTrace();
  		return 0;
		}
    
    return distance;
  }
  
  
}


