package com.nadeul.ndj.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.dto.ReviewDto;
import com.nadeul.ndj.entity.Post;
import com.nadeul.ndj.entity.Review;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "ReviewService", description = "여행리뷰 서비스 API")
@RestController
@RequestMapping("/api/v2/review")
@RequiredArgsConstructor
public class ReviewController<T> {
	
	private final ReviewService<T> reviewService;
	
	
	@GetMapping("/list")
	@Operation(summary = "여행 리뷰 조회", description = "여행 리뷰 목록 조회")
    public ResponseEntity<ApiResponse<ReviewDto.ListResponse>> list(ReviewDto.ListRequest request , Pageable pageable) {
        return ResponseEntity.ok(reviewService.list(request,pageable));
    }
	
	@GetMapping("/myList")
	@Operation(summary = "나의 여행 리뷰 조회", description = "내가 등록한 여행 리뷰 목록 조회")
    public ResponseEntity<ApiResponse<List<Review>>> myList(Pageable pageable) {
        return ResponseEntity.ok(reviewService.myList(pageable));
    }
	
	@GetMapping("/bsetList")
	@Operation(summary = "실시간 인기 리뷰 조회", description = "실시간 인기 리뷰 조회 작성일자 , 리뷰 수로 내림차순 정렬")
    public ResponseEntity<ApiResponse<List<Review>>> bsetList(Pageable pageable) {
        return ResponseEntity.ok(reviewService.bestList(pageable));
    }
	
	
	@PostMapping("/")
	@Operation(summary = "여행 리뷰 등록", description = "여행 리뷰 게시물 등록")
    public ResponseEntity<ApiResponse<T>> createReview(ReviewDto.CreateUpdateDto request,
    													  @RequestParam(value = "reviewImage" , required = false) MultipartFile reviewImage
    		) {
		if(request.getContentId() == null || request.getContentId().toString().equals("리뷰 컨텐츠 번호")) {
	  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"리뷰 컨텐츠 번호"));
	  	}
		
		request.setReviewImage(reviewImage);
        return ResponseEntity.ok(reviewService.createReview(request));
    }
	
	
	@PutMapping("/")
	@Operation(summary = "여행 리뷰 수정", description = "여행 리뷰 게시물 수정")
    public ResponseEntity<ApiResponse<Post>> updateReview( 
    		ReviewDto.CreateUpdateDto request,
			  @RequestParam(value = "reviewImage" , required = false) MultipartFile reviewImage) {
		if(request.getRvId() == null || request.getRvId().toString().equals("")) {
	  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"리뷰 번호"));
	  	}
		
		if(request.getContentId() == null || request.getContentId().toString().equals("리뷰 컨텐츠 번호")) {
	  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"리뷰 컨텐츠 번호"));
	  	}
		
		if(request.getContent() == null || request.getContent().toString().equals("리뷰 내용")) {
	  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"리뷰 내용"));
	  	}
		
		request.setReviewImage(reviewImage);
        return ResponseEntity.ok(reviewService.updateReview(request));
    }
	
	@DeleteMapping("/{id}")
	@Operation(summary = "여행 리뷰 삭제", description = "여행 리뷰 게시물 삭제")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable("id") Integer id) {
		reviewService.deleteReview(id);
        return ResponseEntity.ok(ApiResponse.successResponse(ApiResponseEnum.SUCCESS, null, null, null));
    }
	
	@PostMapping("/reviewLike")
	@Operation(summary = "여행 리뷰 좋아요", description = "해당 리뷰를 좋아요 했을 경우에는 삭제 아닐 경우에는 좋아요")
    public ResponseEntity<ApiResponse<T>> reviewLike(@RequestBody ReviewDto.reviewLikeDto request) {
		if(request.getRvId() == null || request.getRvId().toString().equals("")) {
	  		return ResponseEntity.ok(ApiResponse.failResponse(ApiResponseEnum.VALIDATION_FAILED,"리뷰 번호"));
	  	}
        return ResponseEntity.ok(reviewService.reviewLike(request));
    }
	
}
