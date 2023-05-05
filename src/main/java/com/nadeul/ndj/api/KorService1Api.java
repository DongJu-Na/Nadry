package com.nadeul.ndj.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author DONGJU
 * 한국관광공사_국문 관광정보 서비스_GW
 */
public interface KorService1Api {
	
	//위치기반 관광정보조회
	@GET("/B551011/KorService1/locationBasedList1")
	Call<Map<String,Object>> locationBasedList1(@QueryMap Map<String, Object> param);
	
	//키워드 검색 조회
	@GET("/B551011/KorService1/searchKeyword1")
	Call<Map<String,Object>> searchKeyword1(@QueryMap Map<String, Object> param);
	
	//행사정보조회
	@GET("/B551011/KorService1/searchFestival1")
	Call<Map<String,Object>> searchFestival1(@QueryMap Map<String, Object> param);
	
	//숙박정보조회
	@GET("/B551011/KorService1/searchStay1")
	Call<Map<String,Object>> searchStay1(@QueryMap Map<String, Object> param);
	
	//공통정보조회
	@GET("/B551011/KorService1/detailCommon1")
	Call<Map<String,Object>> detailCommon1(@QueryMap Map<String, Object> param);

	//소개정보조회
	@GET("/B551011/KorService1/detailIntro1")
	Call<Map<String,Object>> detailIntro1(@QueryMap Map<String, Object> param);
	
	//반복정보조회
	@GET("/B551011/KorService1/detailInfo1")
	Call<Map<String,Object>> detailInfo1(@QueryMap Map<String, Object> param);
	
	//이미지정보조회
	@GET("/B551011/KorService1/detailImage1")
	Call<Map<String,Object>> detailImage1(@QueryMap Map<String, Object> param);
	
	//관광정보 동기화 목록 조회
	@GET("/B551011/KorService1/areaBasedSyncList1")
	Call<Map<String,Object>> areaBasedSyncList1(@QueryMap Map<String, Object> param);
	
	//지역코드 조회
	@GET("/B551011/KorService1/areaCode1")
	Call<Map<String,Object>> areaCode1(@QueryMap Map<String, Object> param);
	
	//반려동물 동반 여행 정보
	@GET("/B551011/KorService1/detailPetTour1")
	Call<Map<String,Object>> detailPetTour1(@QueryMap Map<String, Object> param);

	//서비스분류코드조회
	@GET("/B551011/KorService1/categoryCode1")
	Call<Map<String,Object>> categoryCode1(@QueryMap Map<String, Object> param);
	
	//지역기반 관광정보조회
	@GET("/B551011/KorService1/areaBasedList1")
	Call<Map<String,Object>> areaBasedList1(@QueryMap Map<String, Object> param);
	
	
	
	  
}
