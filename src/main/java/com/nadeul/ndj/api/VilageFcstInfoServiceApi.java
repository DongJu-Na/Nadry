package com.nadeul.ndj.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author DONGJU
 * 초단기실황, 초단기예보, 단기예보, 예보버전 정보를 조회하는 서비스입니다. 
 */	
public interface VilageFcstInfoServiceApi {
	
	//초단기실황조회
	@GET("/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst")
	Call<Map<String,Object>> getUltraSrtNcst(@QueryMap Map<String, Object> param);
	
	//초단기예보조회
	@GET("/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst")
	Call<Map<String,Object>> getUltraSrtFcst(@QueryMap Map<String, Object> param);
	
	//단기예보조회
	@GET("/1360000/VilageFcstInfoService_2.0/getVilageFcst")
	Call<Map<String,Object>> getVilageFcst(@QueryMap Map<String, Object> param);
	
	//예보버전조회
	@GET("/1360000/VilageFcstInfoService_2.0/getFcstVersion")
	Call<Map<String,Object>> getFcstVersion(@QueryMap Map<String, Object> param);
	
}