package com.nadeul.ndj.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author DONGJU
 * 한국관광공사가 보유하고 있는 관광사진갤러리 콘텐츠의 사진 제목,촬영장소,촬영일 등의 정보
 */
public interface PhotoGalleryServiceApi {
	
	//관광사진갤러리목록조회
	@GET("/B551011/PhotoGalleryService1/galleryList1")
	Call<Map<String,Object>> galleryList1(@QueryMap Map<String, Object> param);
	
	//관광사진갤러리 키워드검색 목록 조회
	@GET("/B551011/PhotoGalleryService1/gallerySearchList1")
	Call<Map<String,Object>> gallerySearchList1(@QueryMap Map<String, Object> param);
	
	//관광사진갤러리 상세 목록 조회
	@GET("/B551011/PhotoGalleryService1/galleryDetailList1")
	Call<Map<String,Object>> galleryDetailList1(@QueryMap Map<String, Object> param);
	
	//관광사진정보 동기화 조회
	@GET("/B551011/PhotoGalleryService1/gallerySyncDetailList1")
	Call<Map<String,Object>> gallerySyncDetailList1(@QueryMap Map<String, Object> param);
	

	
}