package com.nadeul.ndj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.service.GreenTourInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DONGJU
 * 
 */
@Slf4j
@Tag(name = "GreenTourInfoService", description = "생태관광 정보 서비스 API")
@RequestMapping("/api/v1/greenTour")
@RestController
public class GreenTourInfoServiceApiController {
	
	@Autowired
	GreenTourInfoService greenTourInfoServiceApiService;
	
	@PostMapping("/areaCode1")
	@Operation(summary = "지역코드조회", description = "지역코드목록을 지역,시군구,읍면동 코드목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> areaCode1(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ " \"areaCode\" : \"1\"\r\n"
					+ "}" , description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "areaCode	지역코드	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return greenTourInfoServiceApiService.areaCode1(requestParam);
	}
	
	@PostMapping("/areaBasedList1")
	@Operation(summary = "지역기반 관광정보조회", description = "지역 및 시군구를 기반으로 생태관광정보 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> areaBasedList1(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"areaCode\" : \"2\",\r\n"
					+ "\"sigunguCode\" : \"98\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "areaCode	지역코드	선택<br/>"
							+ "sigunguCode	시군구코드	선택<br/>" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		return greenTourInfoServiceApiService.areaBasedList1(requestParam);
	}
	
	@PostMapping("/areaBasedSyncList1")
	@Operation(summary = "생태관광정보 동기화 목록 조회", description = "생태관광정보 동기화 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> areaBasedSyncList1(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"arrange\" : \"\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"showflag\" : \"1\",\r\n"
					+ "\"modifiedtime\" : \"\",\r\n"
					+ "\"areaCode\" : \"\",\r\n"
					+ "\"sigunguCode\" : \"\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "arrange	정렬 구분(A=제목순, C=수정일순, D=생성일순)	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "showflag	컨텐츠표출여부(1=표출, 0=비표출)	선택<br/>"
							+ "modifiedtime	수정일	선택<br/>"
							+ "areaCode	지역코드	선택<br/>"
							+ "sigunguCode	시군구코드	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return greenTourInfoServiceApiService.areaBasedSyncList1(requestParam);
	}	

 
}
