package com.nadeul.ndj.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @author DONGJU
 * 무장애여행 
 * 국민복지관광 서비스(취학계층의 장애요인 해소)-장애인, 어르신,영유아를 동반한 여행의 어려움 해소를 위한 관광정보
 */
public interface BarrierFreeTourInfoServiceApi {
	
	//지역코드 조회
	@GET("/B551011/KorWithService1/areaCode1")
	Call<Map<String,Object>> areaCode1(@QueryMap Map<String, Object> param);
	
	//서비스분류코드조회
	@GET("/B551011/KorWithService1/categoryCode1")
	Call<Map<String,Object>> categoryCode1(@QueryMap Map<String, Object> param);
	
	//지역기반 관광정보조회
	@GET("/B551011/KorWithService1/areaBasedList1")
	Call<Map<String,Object>> areaBasedList1(@QueryMap Map<String, Object> param);
	
	//위치기반 관광정보조회
	@GET("/B551011/KorWithService1/locationBasedList1")
	Call<Map<String,Object>> locationBasedList1(@QueryMap Map<String, Object> param );
	
	//키워드 검색 조회
	@GET("/B551011/KorWithService1/searchKeyword1")
	Call<Map<String,Object>> searchKeyword1(@QueryMap Map<String, Object> param);
	
	//공통정보조회
	@GET("/B551011/KorWithService1/detailCommon1")
	Call<Map<String,Object>> detailCommon1(@QueryMap Map<String, Object> param);
	
	//소개정보조회
	@GET("/B551011/KorWithService1/detailIntro1")
	Call<Map<String,Object>> detailIntro1(@QueryMap Map<String, Object> param);
	
	//반복정보조회
	@GET("/B551011/KorWithService1/detailInfo1")
	Call<Map<String,Object>> detailInfo1(@QueryMap Map<String, Object> param);
	
	//이미지정보조회
	@GET("/B551011/KorWithService1/detailImage1")
	Call<Map<String,Object>> detailImage1(@QueryMap Map<String, Object> param);
	
	//무장애여행조회
	@GET("/B551011/KorWithService1/detailWithTour1")
	Call<Map<String,Object>> detailWithTour1(@QueryMap Map<String, Object> param);
	
	//무장애여행정보동기화목록조회
	@GET("/B551011/KorWithService1/areaBasedSyncList1")
	Call<Map<String,Object>> areaBasedSyncList1( @QueryMap Map<String, Object> param);
	
}