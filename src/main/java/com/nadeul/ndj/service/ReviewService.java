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
import com.nadeul.ndj.dto.ReviewDto;
import com.nadeul.ndj.dto.ReviewDto.ListResponse;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.entity.Post;
import com.nadeul.ndj.entity.Review;
import com.nadeul.ndj.entity.ReviewGrade;
import com.nadeul.ndj.entity.ReviewLike;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.MemberRepository;
import com.nadeul.ndj.repository.ReviewGradeRepository;
import com.nadeul.ndj.repository.ReviewLikeRepository;
import com.nadeul.ndj.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService<T> {
	 private final MemberRepository memberRepository;
	 private final ReviewRepository reviewRepository;
	 private final ReviewLikeRepository reviewLikeRepository;
	 private final ReviewGradeRepository reviewGradeRepository;
	 
	 @Value("${file.savePath}")
	 private String savePath;
	  
	public ApiResponse<ListResponse> list(ReviewDto.ListRequest request,Pageable pageable) {
		Page<Review> reviews = reviewRepository.findByContentId(request.getContentId().toString(),pageable);
		List<ReviewDto> reviewList =  reviews.getContent().stream().map(review ->{
			System.out.println(review.getCreateBy());
		    return new ReviewDto(review);
		} ).collect(Collectors.toList());
        int totalLikes = reviewRepository.countReviewsByContentId(request.getContentId().toString());
        BigDecimal averageRating = reviewRepository.findAverageRatingByContentId(request.getContentId().toString());
        BigDecimal averageRatingValue = new BigDecimal("0.0");
        if(!(averageRating == null)) {
        	averageRatingValue = averageRating;
        }
        ReviewDto.ListResponse response = new ReviewDto.ListResponse(reviewList, averageRatingValue, totalLikes);
		  
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, response, null, null);
	 }
	
	public ApiResponse<List<Review>> myList(Pageable pageable) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        
        String email = ((UserDetails) principal).getUsername();
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (!optionalMember.isPresent()) {
            // 사용자 정보를 찾을 수 없을 때 에러 반환
            return ApiResponse.errorResponse(ApiResponseEnum.UNKNOWN_MEMBER);
        }
        
		Page<Review> reviews = reviewRepository.findByMemberMemId(optionalMember.get().getMemId(),pageable);
		List<Review> reviewList = reviews.getContent(); 
		  
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
        	Optional<Review> reivew = reviewRepository.findById(Integer.parseInt(request.getRvId()));
        	
        	ReviewLike reviewLike = ReviewLike.builder()
        			                .review(reivew.get())
        			                .member(member)
        			                .likes(1)
        			                .likeDate(LocalDateTime.now())
        			                .build();
        	
        	reviewLikeRepository.save(reviewLike);						 
        }
        
        return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, null, null, null);
	}
}
