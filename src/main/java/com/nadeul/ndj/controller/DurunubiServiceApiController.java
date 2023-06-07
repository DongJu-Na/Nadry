package com.nadeul.ndj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.service.DurunubiService;

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
@Tag(name = "DurunubiServiceApi", description = "두루누비 정보 서비스 API")
@RequestMapping("/api/v1/Durunubi")
@RestController
public class DurunubiServiceApiController {
	
	@Autowired
	DurunubiService durunubiService;
	
	@PostMapping("/routeList")
	@Operation(summary = "길 목록 정보 조회", description = "길 목록 정보를 조회하는 기능")
	public ResponseEntity<Map<String,Object>> routeList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"themeNm\" : \"천지인\",\r\n"
					+ "\"brdDiv\" : \"DNWW\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	필수<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "themeNm	길 명	선택<br/>"
							+ "brdDiv	걷기/자전거 구분(DNWW : 걷기길 , DNBW : 자전거길)	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return durunubiService.routeList(requestParam);
	}
	
	@PostMapping("/courseList")
	@Operation(summary = "코스 목록 정보 조회", description = "코스 목록 정보를 조회하는 기능")
	public ResponseEntity<Map<String,Object>> courseList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"crsKorNm\" : \"밀양강\",\r\n"
					+ "\"routeIdx\" : \"\",\r\n"
					+ "\"crsLevel\" : \"2\",\r\n"
					+ "\"brdDiv\" : \"DNBW\"\r\n"
					+ "}", description = "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "crsKorNm	코스명	선택<br/>"
							+ "routeIdx	길 고유번호	선택<br/>"
							+ "crsLevel	코스 난이도(1:하/2:중/3:상)	선택<br/>"
							+ "brdDiv	걷기/자전거 구분 (DNWW : 걷기길, DNBW : 자전거길)	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return durunubiService.courseList(requestParam);
	}
	
 
}
