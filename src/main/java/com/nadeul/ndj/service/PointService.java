package com.nadeul.ndj.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.PointEarnDto;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.entity.Point;
import com.nadeul.ndj.entity.PointHistory;
import com.nadeul.ndj.entity.Trip;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.PointHistoryRepository;
import com.nadeul.ndj.repository.PointRepository;
import com.nadeul.ndj.repository.TripRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointService<T> {
	private static final int EARTH_RADIUS = 6371; // 지구의 반지름 (단위: km)
	private final TripRepository tripRepository;
	private final PointRepository pointRepository;
	private final PointHistoryRepository pointHistoryRepository;
	
	
	public ApiResponse<T> earn(PointEarnDto dto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		
		if (principal instanceof UserDetails) {
		    UserDetails userDetails = (UserDetails) principal;
		    Member member = (Member) userDetails;
		    
				// process 0 거리가 유효거리 50m안으로 들어왔는지 체크
				if(calculateDistance(dto.getRealPosX(), dto.getRealPosY(), dto.getPosX(), dto.getPosY()) < 50) {
					return ApiResponse.failResponse(ApiResponseEnum.POSITION_UNAVAILABLE, ""); 
				}
		    
		    // process 1 여행 이력 Insert
				var trip = Trip.builder()
							.memId(member)
							.posX(dto.getPosX())
							.posY(dto.getPosY())
							.realPosX(dto.getRealPosX())
							.realPosY(dto.getRealPosY())
							.tripDate(dto.getTripDate())
							.contentId(dto.getContentId())
							.build();
				
				var saveTripHistory = tripRepository.save(trip);
				
				// process 2 최종 포인트 merge 
				var pointEt =  pointRepository.findByPoint(member);
				
				var point = Point.builder()
						 .point(pointEt.map(p -> p.getPoint() + 10).orElse(0))
             .memId(member)
             .blackYn(pointEt.flatMap(p -> Optional.ofNullable(p.getBlackYn())).orElse("N"))
             .build();
				
				var savePoint = pointRepository.save(point);
				
				// process 3 포인트 히스토리 Insert
				var pointHistory = PointHistory.builder()
						.poId(point.getPoId())
						.build();
				
				var savePointHistory = pointHistoryRepository.save(pointHistory);
				
		} else {
		    // principal이 UserDetails 타입이 아닌 경우 처리 로직 -> 응답 객체를 null 로 반환
			return ApiResponse.failResponse(ApiResponseEnum.UNKNOWN_MEMBER, ""); 
		}
		
		
    return ApiResponse.successResponse(ApiResponseEnum.SUCCESS,null,null,null);
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


