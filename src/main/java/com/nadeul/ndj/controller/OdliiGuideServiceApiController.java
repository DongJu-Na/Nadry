package com.nadeul.ndj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.service.OdliiGuideService;

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
@Tag(name = "OdliiGuideService", description = "관광지 오디오 가이드 정보 서비스 API")
@RequestMapping("/api/v1/odlii")
@RestController
public class OdliiGuideServiceApiController {
	
	@Autowired
	OdliiGuideService odliiGuideService;
	
	@PostMapping("/themeBasedList")
	@Operation(summary = "관광지 기본 정보 목록 조회", description = "관광지 기본정보 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> themeBasedList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"langCode\" : \"ko\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "langCode	언어	필수" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return odliiGuideService.themeBasedList(requestParam);
	}
	
	@PostMapping("/themeLocationBasedList")
	@Operation(summary = "관광지 위치기반 정보 목록 조회", description = "내주변 좌표를 기반으로 관광지 정보 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> themeLocationBasedList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"mapX\" : \"126.615455\",\r\n"
					+ "\"mapY\" : \"34.476566\",\r\n"
					+ "\"radius\" : \"1000\",\r\n"
					+ "\"langCode\" : \"ko\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "mapX	경도	필수<br/>"
							+ "mapY	위도	필수<br/>"
							+ "radius	거리 반경	필수<br/>"
							+ "langCode	언어	필수" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return odliiGuideService.themeLocationBasedList(requestParam);
	}
	
	@PostMapping("/themeSearchList")
	@Operation(summary = "관광지 키워드 검색 목록 조회", description = "키워드로 검색을 하여 관광지정보 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> themeSearchList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"keyword\" : \"대흥사\",\r\n"
					+ "\"langCode\" : \"ko\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택\r\n"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	 필수<br/>"
							+ "MobileApp	서비스명 필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "keyword	요청 키워드	필수<br/>"
							+ "langCode	언어	필수<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return odliiGuideService.themeSearchList(requestParam);
	}
	
	@PostMapping("/storyBasedList")
	@Operation(summary = "이야기 기본 정보 목록 조회", description = "이야기 기본정보 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> storyBasedList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"langCode\" : \"ko\",\r\n"
					+ "\"tid\" : \"2588\",\r\n"
					+ "\"tlid\" : \"2588\"\r\n"
					+ "}", description = "numOfRows 한 페이지 결과 수 선택<br/>"
							+ "pageNo 페이지 번호 선택<br/>"
							+ "MobileOS OS 구분 필수<br/>"
							+ "MobileApp 서비스명 필수<br/>"
							+ "serviceKey 인증키 필수<br/>"
							+ "_type json 선택<br/>"
							+ "langCode 언어 필수<br/>"
							+ "tid 관광지아이디 선택<br/>"
							+ "tlid 관광지언어아이디 선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return odliiGuideService.storyBasedList(requestParam);
	}
	
	@PostMapping("/storyLocationBasedList")
	@Operation(summary = "이야기 위치기반 정보 목록 조회", description = "내주변 좌표를 기반으로 이야기정보 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> storyLocationBasedList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"mapX\" : \"126.615455\",\r\n"
					+ "\"mapY\" : \"34.476566\",\r\n"
					+ "\"radius\" : \"1000\",\r\n"
					+ "\"langCode\" : \"ko\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "mapX	경도	필수<br/>"
							+ "mapY	위도	필수<br/>"
							+ "radius	거리 반경	필수<br/>"
							+ "langCode	언어	필수<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return odliiGuideService.storyLocationBasedList(requestParam);
	}
	
	@PostMapping("/storySearchList")
	@Operation(summary = "이야기 키워드 검색 목록 조회", description = "키워드로 검색을 하여 이야기 정보 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> storySearchList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"keyword\" : \"대흥사\",\r\n"
					+ "\"langCode\" : \"ko\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "keyword	요청 키워드	필수<br/>"
							+ "langCode	언어	필수<br/>" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return odliiGuideService.storySearchList(requestParam);
	}
	
	@PostMapping("/themeBaseSyncdList")
	@Operation(summary = "오디 관광지정보 동기화 목록 조회", description = "오디 관광지정보 동기화 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> themeBaseSyncdList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"langCode\" : \"ko\",\r\n"
					+ "\"syncStatus\" : \"A\",\r\n"
					+ "\"modifiedtime\" : \"202203\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "langCode	언어	필수<br/>"
							+ "syncStatus	컨텐츠상태	선택<br/>"
							+ "modifiedtime	수정일	선택" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return odliiGuideService.themeBaseSyncdList(requestParam);
	}
	
	@PostMapping("/storyBasedSyncList")
	@Operation(summary = "오디 이야기정보 동기화 목록 조회", description = "오디 이야기정보 동기화 목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> storyBasedSyncList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"langCode\" : \"ko\",\r\n"
					+ "\"tid\" : \"246\",\r\n"
					+ "\"tlid\" : \"701\",\r\n"
					+ "\"syncStatus\" : \"U\",\r\n"
					+ "\"modifiedtime\" : \"\"\r\n"
					+ "}", description = "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "langCode	언어	필수<br/>"
							+ "tid	관광지아이디	선택<br/>"
							+ "tlid	관광지언어아이디	선택<br/>"
							+ "syncStatus	컨텐츠상태	선택<br/>"
							+ "modifiedtime	수정일	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return odliiGuideService.storyBasedSyncList(requestParam);
	}
	
 
}
