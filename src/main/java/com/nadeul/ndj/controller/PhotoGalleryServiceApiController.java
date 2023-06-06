package com.nadeul.ndj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.service.PhotoGalleryService;

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
@Tag(name = "PhotoGalleryService", description = "관광사진갤러리 서비스 API")
@RequestMapping("/api/v1/photoGallery")
@RestController
public class PhotoGalleryServiceApiController {
	
	@Autowired
	PhotoGalleryService photoGalleryService;
	
	@PostMapping("/galleryList1")
	@Operation(summary = "관광사진갤러리 목록 조회", description = "사진갤러리 목록을 조회하는 기능입니다. 제목으로 중복 콘텐츠를 제거하여 그룹화하고, 사진의 URL경로, 촬영월, 촬영장소 등의 내용을 목록으로 제공")
	public ResponseEntity<Map<String,Object>> galleryList1(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"1\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" :	\"인증키\",\r\n"
					+ "\"_type\" :	\"0\",	\r\n"
					+ "\"arrange\" :	\"A\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "arrange	정렬 구분	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return photoGalleryService.galleryList1(requestParam);
	}
	
	@PostMapping("/gallerySearchList1")
	@Operation(summary = "관광사진갤러리 키워드 검색 목록 조회", description = "키워드검색을 통해 사진갤러리 목록을 조회하는 기능입니다. 키워드검색을 통해 키워드 항목데이터와 매칭되는 정보를 목록으로 표출하며, 제목에 해당하는 그룹화된 목록을 제공")
	public ResponseEntity<Map<String,Object>> gallerySearchList1(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"1\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"arrange\" : \"\",\r\n"
					+ "\"keyword\" : \"\"\r\n"
					+ "}", description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "arrange	정렬 구분	선택<br/>"
							+ "keyword	요청 키워드	필수<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		return photoGalleryService.gallerySearchList1(requestParam);
	}
	
	@PostMapping("/galleryDetailList1")
	@Operation(summary = "관광사진갤러리 상세 목록 조회", description = "사진갤러리 상세 목록을 조회하는 기능입니다. 사진갤러리 목록 조회를 통해 제목에 해당하는 그룹화된 목록이며, 사진의 URL경로, 촬영월, 촬영장소 등의 내용을 목록 제공")
	public ResponseEntity<Map<String,Object>> galleryDetailList1(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"5\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"title\" : \"경복궁\"\r\n"
					+ "}" , description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "title	요청 키워드	필수<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return photoGalleryService.galleryDetailList1(requestParam);
	}	
	
	@PostMapping("/gallerySyncDetailList1")
	@Operation(summary = "관광사진갤러리 동기화 목록 조회", description = "사진갤러리 상세 내용을 동기화하여 목록 조회")
	public ResponseEntity<Map<String,Object>> gallerySyncDetailList1(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"galUseFlag\" : \"1\",\r\n"
					+ "\"galModifiedTime\" : \"\",\r\n"
					+ "\"title\" : \"청설모\"\r\n"
					+ "}" , description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "galUseFlag	표출여부	선택<br/>"
							+ "galModifiedTime	수정일	선택<br/>"
							+ "title	요청 키워드	필수<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return photoGalleryService.gallerySyncDetailList1(requestParam);
	}	

 
}
