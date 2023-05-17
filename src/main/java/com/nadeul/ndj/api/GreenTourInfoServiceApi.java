package com.nadeul.ndj.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author DONGJU
 * 한국관광공사생태관광정보서비스
 * 한국관광공사가보유하고있는생태관광정보를지역별로제목,개요,연락처등의정보
 */
public interface GreenTourInfoServiceApi {
	
	//지역코드조회
	@GET("/B551011/GreenTourService1/areaCode1")
	Call<Map<String,Object>> areaCode1(@QueryMap Map<String, Object> param);
	
	//지역기반생태관광정보조회
	@GET("/B551011/GreenTourService1/areaBasedList1")
	Call<Map<String,Object>> areaBasedList1(@QueryMap Map<String, Object> param);
	
	//생태관광정보동기화조회
	@GET("/B551011/GreenTourService1/areaBasedSyncList1")
	Call<Map<String,Object>> areaBasedSyncList1(@QueryMap Map<String, Object> param);
	
}