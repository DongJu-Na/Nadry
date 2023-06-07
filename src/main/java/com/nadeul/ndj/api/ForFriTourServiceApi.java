package com.nadeul.ndj.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author DONGJU
 * 한국관광공사가 수집한 동남아시아,중동관광객을 대상 주요 관광지 주변에 식당,기도실 등 편의시설 정보를국문으로제공합니다
 */	
public interface ForFriTourServiceApi {
	
	//지역코드조회
	@GET("/B551011/Durunubi/areaCode")
	Call<Map<String,Object>> areaCode(@QueryMap Map<String, Object> param);
	
	//지역기반관광정보조회
	@GET("/B551011/Durunubi/areaBasedList")
	Call<Map<String,Object>> areaBasedList(@QueryMap Map<String, Object> param);
	
	//위치기반관광정보조회
	@GET("/B551011/Durunubi/locationBasedList")
	Call<Map<String,Object>> locationBasedList(@QueryMap Map<String, Object> param);

	//키워드검색조회
	@GET("/B551011/Durunubi/searchKeyword")
	Call<Map<String,Object>> searchKeyword(@QueryMap Map<String, Object> param);
	
	//공통정보조회 (상세정보1)
	@GET("/B551011/Durunubi/detailCommon")
	Call<Map<String,Object>> detailCommon(@QueryMap Map<String, Object> param);
		
	//소개정보조회 (상세정보2)
	@GET("/B551011/Durunubi/detailIntro")
	Call<Map<String,Object>> detailIntro(@QueryMap Map<String, Object> param);
	
	//이미지정보조회 (상세정보3)
	@GET("/B551011/Durunubi/detailImage")
	Call<Map<String,Object>> detailImage(@QueryMap Map<String, Object> param);
}