package com.nadeul.ndj.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author DONGJU
 * 한국어, 영어, 중국어, 일본어로 해설해주는 오디오 가이드 서비스인 ‘오디(Odii)’의 음성, 대본, 사진 정보를 제공합니다.
 */	
public interface OdliiGuideServiceApi {
	
	//관광지 기본 정보 목록 조회
	@GET("/B551011/Odii/themeBasedList")
	Call<Map<String,Object>> themeBasedList(@QueryMap Map<String, Object> param);
	
	//관광지 위치기반 정보 목록 조회
	@GET("/B551011/Odii/themeLocationBasedList")
	Call<Map<String,Object>> themeLocationBasedList(@QueryMap Map<String, Object> param);
	
	//관광지 키워드 검색 목록 조회
	@GET("/B551011/Odii/themeSearchList")
	Call<Map<String,Object>> themeSearchList(@QueryMap Map<String, Object> param);
	
	//이야기 기본 정보 목록 조회
	@GET("/B551011/Odii/storyBasedList")
	Call<Map<String,Object>> storyBasedList(@QueryMap Map<String, Object> param);
	
	//이야기 위치기반 정보 목록 조회
	@GET("/B551011/Odii/storyLocationBasedList")
	Call<Map<String,Object>> storyLocationBasedList(@QueryMap Map<String, Object> param);
	
	//이야기 키워드 검색 목록 조회
	@GET("/B551011/Odii/storySearchList")
	Call<Map<String,Object>> storySearchList(@QueryMap Map<String, Object> param);
	
	//오디 관광지정보 동기화 목록 조회
	@GET("/B551011/Odii/themeBaseSyncdList")
	Call<Map<String,Object>> themeBaseSyncdList(@QueryMap Map<String, Object> param);
	
	//오디 이야기정보 동기화 목록 조회
	@GET("/B551011/Odii/storyBasedSyncList")
	Call<Map<String,Object>> storyBasedSyncList(@QueryMap Map<String, Object> param);

	
}