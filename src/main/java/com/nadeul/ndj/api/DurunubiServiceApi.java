package com.nadeul.ndj.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author DONGJU
 * 걷기, 자전거 등 인간의 힘을 이용한 레저여행에 대해 코스정보를 중심으로 주변 관광정보를 종합 제공하는 통합여행정보 서비스인 '두루누비'의 길 정보와 코스 정보를 제공합니다.
 */	
public interface DurunubiServiceApi {
	
	//길 목록 정보 조회
	@GET("/B551011/Durunubi/routeList")
	Call<Map<String,Object>> routeList(@QueryMap Map<String, Object> param);
	
	//코스 목록 정보 조회
	@GET("/B551011/Durunubi/courseList")
	Call<Map<String,Object>> courseList(@QueryMap Map<String, Object> param);
	
}