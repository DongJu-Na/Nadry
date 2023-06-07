package com.nadeul.ndj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.service.ForFriTourService;

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
@Tag(name = "ForFriTourServiceApi", description = "외래객 친화 관광 정보 서비스 API")
@RequestMapping("/api/v1/forFriTour")
@RestController
public class ForFriTourServiceApiController {
	
	@Autowired
	ForFriTourService forFriTourService;
	
	@PostMapping("/areaCode")
	@Operation(summary = "지역코드조회", description = "지역코드, 시군구코드목록을조회하는기능")
	public ResponseEntity<Map<String,Object>> areaCode(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"areaCode\" : \"\",\r\n"
					+ "\"_type\" : \"json\"\r\n"
					+ "}", description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "areaCode	지역코드	선택<br/>"
							+ "_type	응답메세지 형식	선택<br/>" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return forFriTourService.areaCode(requestParam);
	}
	
	@PostMapping("/areaBasedList")
	@Operation(summary = "지역기반관광정보조회", description = "지역및시군구를기반으로관광정보목록을조회하는기능")
	public ResponseEntity<Map<String,Object>> areaBasedList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"arrange\" : \"C\",\r\n"
					+ "\"contentTypeId\" : \"1145\",\r\n"
					+ "\"areaCode\" : \"32\",\r\n"
					+ "\"sigunguCode\" : \"13\",\r\n"
					+ "\"modifiedtime\" : \"20221222\"\r\n"
					+ "}", description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "arrange	정렬구분	선택<br/>"
							+ "contentTypeId	관광타입 ID	선택<br/>"
							+ "areaCode	지역코드	선택<br/>"
							+ "sigunguCode	시군구코드	선택<br/>"
							+ "modifiedtime	수정일	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return forFriTourService.areaBasedList(requestParam);
	}
	
	@PostMapping("/locationBasedList")
	@Operation(summary = "위치기반관광정보조회", description = "내주변좌표를기반으로관광정보목록을조회하는기능")
	public ResponseEntity<Map<String,Object>> locationBasedList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"arrange\" : \"A\",\r\n"
					+ "\"contentTypeId\" : \"1145\",\r\n"
					+ "\"mapX\" : \"127.7276298330\",\r\n"
					+ "\"mapY\" : \"37.8792343699\",\r\n"
					+ "\"radius\" : \"1000\",\r\n"
					+ "\"modifiedtime\" : \"20221222\"\r\n"
					+ "}", description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "arrange	정렬구분	선택<br/>"
							+ "contentTypeId	관광타입 ID	선택<br/>"
							+ "mapX	X좌표	필수<br/>"
							+ "mapY	Y좌표	필수<br/>"
							+ "radius	거리반경	필수<br/>"
							+ "modifiedtime	수정일	선택<br/>" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return forFriTourService.locationBasedList(requestParam);
	}
	
	@PostMapping("/searchKeyword")
	@Operation(summary = "키워드검색조회", description = "키워드로검색을하여관광타입별또는전체목록을조회하는기능")
	public ResponseEntity<Map<String,Object>> searchKeyword(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"arrange\" : \"C\",\r\n"
					+ "\"contentTypeId\" : \"1145\",\r\n"
					+ "\"areaCode\" : \"32\",\r\n"
					+ "\"sigunguCode\" : \"13\",\r\n"
					+ "\"keyword\" : \"DoKkaebi house charcoal grilled chicken ribs\"\r\n"
					+ "}", description = "numOfRows 한페이지결과수 선택<br/>"
							+ "pageNo 페이지번호 선택<br/>"
							+ "MobileOS OS 구분 필수<br/>"
							+ "MobileApp 서비스명 필수<br/>"
							+ "serviceKey 인증키(서비스키) 필수<br/>"
							+ "_type 응답메세지 형식 선택<br/>"
							+ "arrange 정렬구분 선택<br/>"
							+ "contentTypeId 관광타입(1145:음식,1146:기도실) ID 선택<br/>"
							+ "areaCode 지역코드 선택<br/>"
							+ "sigunguCode 시군구코드 선택<br/>"
							+ "keyword 요청키워드 필수<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return forFriTourService.searchKeyword(requestParam);
	}
	
	@PostMapping("/detailCommon")
	@Operation(summary = "공통정보조회", description = "타입별공통정보(제목, 주소, 좌표등)를조회하는기능")
	public ResponseEntity<Map<String,Object>> detailCommon(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"contentId\" : \"264552\",\r\n"
					+ "\"contentTypeId\" : \"1145\"\r\n"
					+ "}", description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "contentId	콘텐츠ID	필수<br/>"
							+ "contentTypeId	관광타입 ID	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return forFriTourService.detailCommon(requestParam);
	}
	
	@PostMapping("/detailIntro")
	@Operation(summary = "소개정보조회", description = "타입별소개정보(할랄메뉴,운영시간,코란 등)를조회하는기능")
	public ResponseEntity<Map<String,Object>> detailIntro(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"contentId\" : \"2523587\",\r\n"
					+ "\"contentTypeId\" : \"1145\"\r\n"
					+ "}", description = "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return forFriTourService.detailIntro(requestParam);
	}
	
	@PostMapping("/detailImage")
	@Operation(summary = "이미지정보조회", description = "관광타입(음식,기도실)에해당하는이미지URL 목록을조회하는기능")
	public ResponseEntity<Map<String,Object>> detailImage(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"contentId\" : \"2523587\",\r\n"
					+ "\"imageYN\" : \"Y\",\r\n"
					+ "\"subImageYN\" : \"Y\",\r\n"
					+ "}", description = "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "contentId	콘텐츠ID	필수<br/>"
							+ "imageYN	이미지조회1(Y=콘텐츠이미지조회N=”음식점”타입의음식메뉴이미지)	선택<br/>"
							+ "subImageYN	이미지조회2(Y=원본,썸네일이미지조회N=Null)	선택" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return forFriTourService.detailImage(requestParam);
	}
 
}
