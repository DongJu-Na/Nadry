package com.nadeul.ndj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.service.VilageFcstInfoService;

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
@Tag(name = "VilageFcstInfoServiceApi", description = "단기예보 조회 서비스 API")
@RequestMapping("/api/v1/Fcst/")
@RestController
public class VilageFcstInfoServiceApiController {
	
	@Autowired
	VilageFcstInfoService vilageFcstInfoService;
	
	
	@PostMapping("/getUltraSrtNcst")
	@Operation(summary = "초단기실황조회", description = "실황정보를 조회하기 위해 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 조회 조건으로 자료구분코드, 실황값, 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 정보를 조회하는 기능")
	public ResponseEntity<Map<String,Object>> getUltraSrtNcst(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"dataType\" : \"json\",\r\n"
					+ "\"base_date\" : \"20230608\",\r\n"
					+ "\"base_time\" : \"0600\",\r\n"
					+ "\"nx\" : \"55\",\r\n"
					+ "\"ny\" : \"127\"\r\n"
					+ "}", description = "serviceKey 인증키 필수<br/>"
							+ "numOfRows 한 페이지 결과 수 필수<br/>"
							+ "pageNo 페이지 번호 필수<br/>"
							+ "dataType 응답자료형식 선택<br/>"
							+ "base_date 발표일자 필수<br/>"
							+ "base_time 발표시각 필수 <br/>"
							+ "nx 예보지점 X 좌표 필수<br/>"
							+ "ny 예보지점 Y 좌표 필수<br/>"
							+ "")))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return vilageFcstInfoService.getUltraSrtNcst(requestParam);
	}
	
	@PostMapping("/getUltraSrtFcst")
	@Operation(summary = "초단기예보조회", description = "초단기예보정보를 조회하기 위해 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 조회 조건으로 자료구분코드, 예보값, 발표일자, 발표시각, 예보지점 X 좌표, 예보지점 Y 좌표의 정보를 조회하는 기능")
	public ResponseEntity<Map<String,Object>> getUltraSrtFcst(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"dataType\" : \"json\",\r\n"
					+ "\"base_date\" : \"20230608\",\r\n"
					+ "\"base_time\" : \"0600\",\r\n"
					+ "\"nx\" : \"55\",\r\n"
					+ "\"ny\" : \"127\"\r\n"
					+ "}", description = "serviceKey 인증키 필수<br/>"
							+ "numOfRows 한 페이지 결과 수 필수<br/>"
							+ "pageNo 페이지 번호 필수<br/>"
							+ "dataType 응답자료형식 선택<br/>"
							+ "base_date 발표일자 필수<br/>"
							+ "base_time 발표시각 필수 <br/>"
							+ "nx 예보지점 X 좌표 필수<br/>"
							+ "ny 예보지점 Y 좌표 필수<br/>"
							+ "")))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return vilageFcstInfoService.getUltraSrtFcst(requestParam);
	}
	
	@PostMapping("/getVilageFcst")
	@Operation(summary = "단기예보조회", description = "단기예보 정보를 조회하기 위해 발표일자, 발표시각, 예보지점 X좌표, 예보지점 Y 좌표의 조회 조건으로 발표일자, 발표시각, 자료구분문자, 예보 값, 예보일자, 예보시각, 예보지점 X 좌표, 예보지점 Y 좌표의 정보를 조회하는 기능")
	public ResponseEntity<Map<String,Object>> getVilageFcst(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"dataType\" : \"json\",\r\n"
					+ "\"base_date\" : \"20230608\",\r\n"
					+ "\"base_time\" : \"0600\",\r\n"
					+ "\"nx\" : \"55\",\r\n"
					+ "\"ny\" : \"127\"\r\n"
					+ "}", description = "serviceKey 인증키 필수<br/>"
							+ "numOfRows 한 페이지 결과 수 필수<br/>"
							+ "pageNo 페이지 번호 필수<br/>"
							+ "dataType 응답자료형식 선택<br/>"
							+ "base_date 발표일자 필수<br/>"
							+ "base_time 발표시각 필수 <br/>"
							+ "nx 예보지점 X 좌표 필수<br/>"
							+ "ny 예보지점 Y 좌표 필수<br/>"
							+ "")))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return vilageFcstInfoService.getVilageFcst(requestParam);
	}
	
	@PostMapping("/getFcstVersion")
	@Operation(summary = "예보버전조회", description = "단기예보정보조회서비스 각각의 오퍼레이션(초단기실황, 초단기예보, 단기예보)들의 수정된 예보 버전을 파악하기 위해 예보버전을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> getFcstVersion(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"serviceKey\" : \"인증키\",\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"dataType\" : \"json\",\r\n"
					+ "\"ftype\" : \"ODAM\",\r\n"
					+ "\"basedatetime\" : \"202306080800\"\r\n"
					+ "}", description = "serviceKey	인증키	필수<br/>"
							+ "numOfRows 한 페이지 결과 수 필수<br/>"
							+ "pageNo 페이지 번호 필수<br/>"
							+ "dataType 응답자료형식 선택<br/>"
							+ "ftype	파일구분(ODAM: 초단기실황 VSRT: 초단기예보 SHRT: 단기예보) 필수<br/>"
							+ "basedatetime 발표일시분 필수<br/>"
							+ "")))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		
		return vilageFcstInfoService.getFcstVersion(requestParam);
	}

}
