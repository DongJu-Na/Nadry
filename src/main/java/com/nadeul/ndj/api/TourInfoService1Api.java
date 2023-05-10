package com.nadeul.ndj.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @author DONGJU
 * 한국관광공사 TourAPI 4.0
 * 국문 관광정보서비스_GW - KorService1
 * 영문 관광정보서비스_GW - EngService1
 * 중문 간체 관광정보서비스_GW ChsService1
 * 중문 번체 관광정보서비스_GW ChtService1
 * 일문 관광정보서비스_GW - JpnService1
 * 독어 관광정보서비스_GW - GerService1
 * 불어 관광정보서비스_GW - FreService1 
 * 서어 관광정보서비스_GW - SpnService1
 * 노어 관광정보서비스_GW - RusService1
 */
public interface TourInfoService1Api {
	
	//위치기반 관광정보조회
	@GET("/B551011/{lang}Service1/locationBasedList1")
	Call<Map<String,Object>> locationBasedList1(@Path("lang") String lang , @QueryMap Map<String, Object> param );
	
	//키워드 검색 조회
	@GET("/B551011/{lang}Service1/searchKeyword1")
	Call<Map<String,Object>> searchKeyword1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	//행사정보조회
	@GET("/B551011/{lang}Service1/searchFestival1")
	Call<Map<String,Object>> searchFestival1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	//숙박정보조회
	@GET("/B551011/{lang}Service1/searchStay1")
	Call<Map<String,Object>> searchStay1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	//공통정보조회
	@GET("/B551011/{lang}Service1/detailCommon1")
	Call<Map<String,Object>> detailCommon1(@Path("lang") String lang , @QueryMap Map<String, Object> param);

	//소개정보조회
	@GET("/B551011/{lang}Service1/detailIntro1")
	Call<Map<String,Object>> detailIntro1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	//반복정보조회
	@GET("/B551011/{lang}Service1/detailInfo1")
	Call<Map<String,Object>> detailInfo1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	//이미지정보조회
	@GET("/B551011/{lang}Service1/detailImage1")
	Call<Map<String,Object>> detailImage1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	//관광정보 동기화 목록 조회
	@GET("/B551011/{lang}Service1/areaBasedSyncList1")
	Call<Map<String,Object>> areaBasedSyncList1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	//지역코드 조회
	@GET("/B551011/{lang}Service1/areaCode1")
	Call<Map<String,Object>> areaCode1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	//반려동물 동반 여행 정보
	@GET("/B551011/{lang}Service1/detailPetTour1")
	Call<Map<String,Object>> detailPetTour1(@Path("lang") String lang , @QueryMap Map<String, Object> param);

	//서비스분류코드조회
	@GET("/B551011/{lang}Service1/categoryCode1")
	Call<Map<String,Object>> categoryCode1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	//지역기반 관광정보조회
	@GET("/B551011/{lang}Service1/areaBasedList1")
	Call<Map<String,Object>> areaBasedList1(@Path("lang") String lang , @QueryMap Map<String, Object> param);
	
	
	
	  
}
