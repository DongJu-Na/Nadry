package com.nadeul.ndj.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
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
	  
	public ApiResponse<ListResponse> list(ReviewDto.ListRequest request) {
        Optional<List<Review>> optionalReviews = Optional.of(reviewRepository.findByContentId(Integer.parseInt(request.getContentId().toString())).orElse(null));
        
        List<Review> reviews = optionalReviews.get();
        int totalLikes = calculateTotalLikes(reviews);
        Optional<Double> averageRating = Optional.ofNullable(reviewRepository.findAverageRatingByContentId(request.getContentId().toString())).orElse(Optional.of(0.0));


        ReviewDto.ListResponse response = new ReviewDto.ListResponse(reviews, averageRating.get(), totalLikes);
		  
		  return ApiResponse.successResponse(ApiResponseEnum.SUCCESS, response, null, null);
	 }
	  
	public ApiResponse<Post> createReview(ReviewDto.CreateUpdateDto request) {
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
		
		String saveSuccessPath = null;
		try {
	    	if(request.getReviewImage() != null) {
	    		String originalFilename = request.getReviewImage().getOriginalFilename();
	            String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
	            byte[] bytes = request.getReviewImage().getBytes();
	            Path imagePath = Paths.get(savePath + uniqueFilename);
	            Files.write(imagePath, bytes);
	            saveSuccessPath = savePath + uniqueFilename;
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
				                   .rating(Double.parseDouble(request.getReviewRating().toString()))
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
            .rating(Double.parseDouble(request.getReviewRating().toString()))
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
	  

    private int calculateTotalLikes(List<Review> reviews) {
        return reviews.stream().mapToInt(Review::getLikes).sum();
    }
}
