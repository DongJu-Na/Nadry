package com.nadeul.ndj.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.BestListResponse;
import com.nadeul.ndj.dto.ReviewDto;
import com.nadeul.ndj.dto.ReviewDto.ListResponse;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.entity.Point;
import com.nadeul.ndj.entity.PointHistory;
import com.nadeul.ndj.entity.Post;
import com.nadeul.ndj.entity.Review;
import com.nadeul.ndj.entity.ReviewGrade;
import com.nadeul.ndj.entity.ReviewLike;
import com.nadeul.ndj.entity.Stamp;
import com.nadeul.ndj.entity.StampMaster;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.enums.Location;
import com.nadeul.ndj.repository.MemberRepository;
import com.nadeul.ndj.repository.PointHistoryRepository;
import com.nadeul.ndj.repository.PointRepository;
import com.nadeul.ndj.repository.ReviewGradeRepository;
import com.nadeul.ndj.repository.ReviewLikeRepository;
import com.nadeul.ndj.repository.ReviewRepository;
import com.nadeul.ndj.repository.StampMasterRepository;
import com.nadeul.ndj.repository.StampRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService<T> {
	 private final MemberRepository memberRepository;
	 private final ReviewRepository reviewRepository;
	 private final ReviewLikeRepository reviewLikeRepository;
	 private final ReviewGradeRepository reviewGradeRepository;
	 
	 private final PointRepository pointRepository;
	 private final PointHistoryRepository pointHistoryRepository;
	 private final StampRepository stampRepository;
	 private final StampMasterRepository stampMasterRepository;
	 
	 @Value("${file.savePath}")
	 private String savePath;
	  
	public ApiResponse<ListResponse> list(ReviewDto.ListRequest request,Pageable pageable) {
		Page<Review> reviews = reviewRepository.findByContentId(request.getContentId().toString(),pageable);
		List<ReviewDto> reviewList =  reviews.getContent().stream().map(review ->{
		    return new ReviewDto(review);
		} ).collect(Collectors.toList());
        //int totalLikes = reviewRepository.countReviewsByContentId(request.getContentId().toString());
        //BigDecimal averageRating = reviewRepository.findAverageRatingByContentId(request.getContentId().toString());
        //BigDecimal averageRatingValue = new BigDecimal("0.0");
        //if(!(averageRating == null)) {
        //	averageRatingValue = averageRating;
        //}
		
        ReviewDto.ListResponse response = new ReviewDto.ListResponse(reviewList);
		  
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, response, null, null);
	 }
	
	public ApiResponse<ListResponse> myList(Pageable pageable) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        
        String email = ((UserDetails) principal).getUsername();
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (!optionalMember.isPresent()) {
            // 사용자 정보를 찾을 수 없을 때 에러 반환
            return ApiResponse.errorResponse(ApiResponseEnum.UNKNOWN_MEMBER);
        }
        
		Page<Review> reviews = reviewRepository.findByMemberMemId(optionalMember.get().getMemId(),pageable);
		List<ReviewDto> reviewList =  reviews.getContent().stream().map(review ->{
			System.out.println(review.getCreateBy());
		    return new ReviewDto(review);
		} ).collect(Collectors.toList()); 
		

        ReviewDto.ListResponse response = new ReviewDto.ListResponse(reviewList);
        
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, response, null, null);
	 }
	
	public ApiResponse<List<BestListResponse>> bestList(Pageable pageable) {
		  Page<BestListResponse> reviews = reviewRepository.findAllWithLikes(pageable);
		  List<BestListResponse> reviewList = reviews.getContent(); 
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, reviewList, null, null);
	 }
	  
	public ApiResponse<T> createReview(ReviewDto.CreateUpdateDto request) {
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
        int reviewValidCnt = reviewRepository.countReviewsByContentIdAndCreateBy(request.getContentId(), email);
        if(reviewValidCnt > 0) {
        	return ApiResponse.failResponse(ApiResponseEnum.DUPLICATION,"여행 리뷰");
        }
		
		String saveSuccessPath = null;
		try {
	    	if(request.getReviewImage() != null) {
	    		String originalFilename = request.getReviewImage().getOriginalFilename();
	            String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
	            byte[] bytes = request.getReviewImage().getBytes();
	            Path imagePath = Paths.get(savePath + uniqueFilename);
	            Files.write(imagePath, bytes);
	            saveSuccessPath = "/images/" + uniqueFilename;
	        }
	    } catch (IOException e) {
	        // 처리 중 에러가 발생하면 적절히 처리
	    	e.printStackTrace();
	    }
		
		 Review reivew = Review.builder()
				        .member(member)
				        .content(request.getContent())
				        .contentId(request.getContentId())
				        .areaCode(request.getAreaCode())
				        .contentTypeId(request.getContentTypeId())
				        .reviewImageUrl(saveSuccessPath)
				        .createBy(member.getEmail())
				        .createDate(LocalDateTime.now())
				        .build();
		
		 ReviewGrade reviewGrade = ReviewGrade.builder()
				 				   .review(reivew)
				                   .member(member)
				                   .rating(new BigDecimal(request.getReviewRating()))
				                   .ratingDate(LocalDateTime.now())
				                   .build();
		 
		
		  reviewRepository.save(reivew);
		  reviewGradeRepository.save(reviewGrade);
		  
		  // 포인트 적립
		  try {
			  if(request.getRealPosX() != null && request.getRealPosY() != null) {
				  if(distance(request.getRealPosX(), request.getRealPosY(), request.getPosX(), request.getPosY(),"kilometer") <= 1.0) {
					  Optional<Point> pointEt =  pointRepository.findByMemberMemId(optionalMember.get().getMemId());
					  
					  Point point = pointEt.map(p -> {
						    	p.setPoint(p.getPoint() + 100);
						    	return p;
							}).orElseGet(() -> {
								Point newPoint = Point.builder()
							            .point(100)
							            .member(member)
							            .blackYn("N")
							            .build();
							    return newPoint;
							});
							
							Point savePoint = pointRepository.save(point);
							// process 3 포인트 히스토리 Insert
							PointHistory pointHistory = PointHistory.builder()
									.point(savePoint)
									.usePoint(100)
									.usedBy(member.getEmail())
									.useDate(LocalDateTime.now())
									.remarks("여행 리뷰 포인트 적립")
									.member(member)
									.build();
							
							pointHistoryRepository.save(pointHistory);
				  }
			  }  
		  }catch (Exception e) {
			log.warn("포인트 적립 중 오류 발생" + request.toString());
			e.printStackTrace();
		  }

		  
		  // 스탬프
		  try {
			  Location location = Location.getByAreaCode(request.getAreaCode());
			  if (location != null) {
				  Optional<Stamp> optionalStamp = stampRepository.findByMemberMemIdAndStampMasterStmId(optionalMember.get().getMemId(),Integer.parseInt(location.getAreaCode()));
				  if(!optionalStamp.isPresent()) {
					  Optional<StampMaster> stampMaster = stampMasterRepository.findByStampType(request.getAreaCode());
					  if(stampMaster.isPresent()) {
						  Stamp stamp = Stamp.builder()
							  		.member(optionalMember.get())
							  		.stampMaster(stampMaster.get())
							  		.stampCollectDate(LocalDateTime.now())
							  		.build();
						  stampRepository.save(stamp);
					  }
				  }
			  } 
		  }catch(Exception e){
			  log.warn("스탬프 적립 중 오류 발생" + request.toString());
			  e.printStackTrace();  
		  }
		  
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, null, null, null);
	}
	  
	@SuppressWarnings("static-access")
	public ApiResponse<Post> updateReview(ReviewDto.CreateUpdateDto request) {
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
        
        
        Optional<Review> reivew = Optional.of(reviewRepository.findByRvIdAndContentIdAndMember_MemId(Integer.parseInt(request.getRvId().toString()), request.getContentId().toString(), member.getMemId())).orElse(null);
        Optional<ReviewGrade> reviewGrade = Optional.of(reviewGradeRepository.findById(reivew.get().getRvId())).orElse(null);
        
        
        
        if(!reviewGrade.isPresent()) {
        	reviewGrade.get().builder()
        	.review(reivew.get())
            .member(member)
            .rating(new BigDecimal(request.getReviewRating()))
            .ratingDate(LocalDateTime.now())
        	.build();
        	reviewGradeRepository.save(reviewGrade.get());
        }
        
        reviewRepository.save(reivew.get());
		return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, null, null, null);
	}
	  
	public void deleteReview(Integer id) {
		reviewRepository.deleteById(id);
		reviewLikeRepository.deleteById(id);
		reviewGradeRepository.deleteById(id);
	}
    
	public ApiResponse<T> reviewLike(ReviewDto.reviewLikeDto request) {
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
        Optional<ReviewLike> reviewLikeValid = reviewLikeRepository.findByMemberMemIdAndReviewRvId(optionalMember.get().getMemId(),request.getRvId().toString());
        if(reviewLikeValid.isPresent()) {
        	// 이미 좋아요 한 경우에는 좋아요 삭제
        	reviewLikeRepository.delete(reviewLikeValid.get());
        }else {
        	Optional<Review> reivew = reviewRepository.findById(request.getRvId());
        	
        	   if (reivew.isPresent()) {
                   Review review = reivew.get();
                   ReviewLike reviewLike = ReviewLike.builder()
                           .review(review)
                           .member(member)
                           .likes(1)
                           .likeDate(LocalDateTime.now())
                           .build();
                   	  
                   reviewLikeRepository.save(reviewLike);
               } else {
                   // 해당 ID에 해당하는 리뷰가 존재하지 않을 때 에러 반환
                   return ApiResponse.errorResponse(ApiResponseEnum.REVIEW_NOT_FOUND);
               }			 
        }
        
        return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, null, null, null);
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

