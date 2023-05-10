package com.nadeul.ndj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.service.TourInfoService1ApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@Tag(name = "TourInfoService", description = "관광정보 서비스 API")
@RequestMapping("/api/v1/tour")
@RestController
public class TourInfoService1ApiController {
	
	@Autowired
	TourInfoService1ApiService korService1ApiService;
	
	@PostMapping("/locationBasedList1/{lang}")
	@Operation(summary = "위치기반 관광정보 조회", description = "위치기반 관광정보파라미터 타입에 따라서 제목순,수정일순,등록일순 정렬검색목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> locationBasedList1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
      @RequestBody
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
      		+ "\"numOfRows\" : \"10\",\r\n"
      		+ "\"pageNo\" : \"1\",\r\n"
      		+ "\"MobileOS\" : \"ETC\",\r\n"
      		+ "\"MobileApp\" : \"AppTest\",\r\n"
      		+ "\"serviceKey\" : \"서비스 키 문자열\",\r\n"
      		+ "\"_type\" : \"json\",\r\n"
      		+ "\"listYN\" : \"Y\",\r\n"
      		+ "\"arrange\" : \"A\",\r\n"
      		+ "\"contentTypeId\" : \"15\",\r\n"
      		+ "\"mapX\" : \"126.981611\",\r\n"
      		+ "\"mapY\" : \"37.568477\",\r\n"
      		+ "\"radius\" : \"1000\",\r\n"
      		+ "\"modifiedtime\" : \"\"\r\n"
      		+ "}" 
      		, description = "numOfRows	한페이지결과수	선택<br/>"
    			+ "pageNo	페이지번호	선택<br/>"
      		+ "MobileOS	OS 구분		필수<br/>"
      		+ "MobileApp	서비스명		필수<br/>"
      		+ "serviceKey	인증키(서비스키)		필수<br/>"
      		+ "_type	응답메세지 형식	선택<br/>"
      		+ "listYN	목록구분	선택<br/>"
      		+ "arrange	정렬구분	선택<br/>"
      		+ "contentTypeId	관광타입 ID	선택<br/>"
      		+ "mapX	X좌표		필수<br/>"
      		+ "mapY	Y좌표		필수<br/>"
      		+ "radius	거리반경	필수<br/>"
      		+ "modifiedtime	수정일	선택<br/>"
      		)))
			Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.locationBasedList1(lang,requestParam);
	}
	
	@PostMapping("/searchKeyword1/{lang}")
	@Operation(summary = "키워드 검색 조회", description = "위치기반 관광정보파라미터 타입에 따라서 제목순,수정일순,등록일순 정렬검색목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> searchKeyword1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"listYN\" : \"Y\",\r\n"
					+ "\"arrange\" : \"A\",\r\n"
					+ "\"contentTypeId\" :  \"12\",\r\n"
					+ "\"areaCode\" : \"\",\r\n"
					+ "\"sigunguCode\" : \"\",\r\n"
					+ "\"cat1\" : \"\",\r\n"
					+ "\"cat2\" : \"\",\r\n"
					+ "\"cat3\" : \"\",\r\n"
					+ "\"keyword\" : \"영종도\"\r\n"
					+ "}" 
					, description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "listYN	목록구분	선택<br/>"
							+ "arrange	정렬구분	선택<br/>"
							+ "contentTypeId	관광타입 ID	선택<br/>"
							+ "areaCode	지역코드	선택<br/>"
							+ "sigunguCode	시군구코드	선택<br/>"
							+ "cat필수	대분류	선택<br/>"
							+ "cat2	중분류	선택<br/>"
							+ "cat3	소분류	선택<br/>"
							+ "keyword	요청키워드	필수<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.searchKeyword1(lang,requestParam);
	}
	
	@PostMapping("/searchFestival1/{lang}")
	@Operation(summary = "행사정보조회", description = "행사정보목록을 조회한다. 컨텐츠 타입이 ‘행사’일 경우에만 유효하다")
	public ResponseEntity<Map<String,Object>> searchFestival1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"listYN\" : \"Y\",\r\n"
					+ "\"arrange\" : \"A\",\r\n"
					+ "\"areaCode\" : \"\",\r\n"
					+ "\"sigunguCode\" : \"\",\r\n"
					+ "\"eventStartDate\" : \"20230501\",\r\n"
					+ "\"eventEndDate\" : \"\",\r\n"
					+ "\"modifiedtime\" : \"\"\r\n"
					+ "}" , description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "listYN	목록구분	선택<br/>"
							+ "arrange	정렬구분	선택<br/>"
							+ "areaCode	지역코드	선택<br/>"
							+ "sigunguCode	시군구코드	선택<br/>"
							+ "eventStartDate	행사시작일	필수<br/>"
							+ "eventEndDate	행사종료일	선택<br/>"
							+ "modifiedtime	수정일	선택" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.searchFestival1(lang,requestParam);
	}
	
	@PostMapping("/searchStay1/{lang}")
	@Operation(summary = "숙박정보조회", description = "숙박정보 검색목록을 조회한다. 컨텐츠 타입이 ‘숙박’일 경우에만 유효하다.")
	public ResponseEntity<Map<String,Object>> searchStay1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" : \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\",\r\n"
					+ "\"serviceKey\" : \"서비스 키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ "\"listYN\" : \"Y\",\r\n"
					+ "\"areaCode\" : \"\",\r\n"
					+ "\"sigunguCode\" : \"\",\r\n"
					+ "\"modifiedtime\" : \"\"\r\n"
					+ "}" , description = "numOfRows		한페이지결과수		선택<br/>\r\n"
							+ "pageNo			페이지번호			선택<br/>\r\n"
							+ "MobileOS		OS 구분			필수<br/>	\r\n"
							+ "MobileApp		서비스명			필수<br/>\r\n"
							+ "serviceKey		인증키(서비스키)		필수<br/>\r\n"
							+ "_type			응답메세지 형식		선택<br/>\r\n"
							+ "listYN			목록구분			선택<br/>\r\n"
							+ "arrange			정렬구분			선택<br/>\r\n"
							+ "areaCode		지역코드			선택<br/>\r\n"
							+ "sigunguCode		시군구코드			선택<br/>\r\n"
							+ "modifiedtime	수정일			선택<br/>\r\n"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.searchStay1(lang,requestParam);
	}
	
	@PostMapping("/detailCommon1/{lang}")
	@Operation(summary = "공통정보조회", description = "타입별공통 정보기본정보,약도이미지,대표이미지,분류정보,지역정보,주소정보,좌표정보,개요정보,길안내정보,이미지정보,연계관광정보목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> detailCommon1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스 키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ " \"contentId\": \"2674675\",\r\n"
					+ " \"contentTypeId\" : \"15\",\r\n"
					+ " \"defaultYN\" : \"Y\",\r\n"
					+ " \"firstImageYN\" : \"\",\r\n"
					+ " \"areacodeYN\" : \"\",\r\n"
					+ " \"catcodeYN\" : \"\",\r\n"
					+ " \"addrinfoYN\" : \"\",\r\n"
					+ " \"mapinfoYN\" : \"\",\r\n"
					+ " \"overviewYN\" : \"\"\r\n"
					+ "}" , description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "contentId	콘텐츠ID	필수<br/>"
							+ "contentTypeId	관광타입 ID	선택<br/>"
							+ "defaultYN	기본정보조회	선택<br/>"
							+ "firstImageYN	대표이미지조회	선택<br/>"
							+ "areacodeYN	지역코드조회	선택<br/>"
							+ "catcodeYN	서비스분류코드조회	선택<br/>"
							+ "addrinfoYN	주소조회	선택<br/>"
							+ "mapinfoYN	좌표조회	선택<br/>"
							+ "overviewYN	개요조회	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailCommon1(lang,requestParam);
	}
	
	@PostMapping("/detailIntro1/{lang}")
	@Operation(summary = "소개정보조회", description = "상세소개 쉬는날, 개장기간 등 내역을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> detailIntro1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ " \"contentId\": \"2674675\",\r\n"
					+ " \"contentTypeId\" : \"15\"\r\n"
					+ "}" , description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "contentId	콘텐츠ID	필수<br/>"
							+ "contentTypeId	관광타입 ID	필수<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailIntro1(lang,requestParam);
	}
	
	@PostMapping("/detailInfo1/{lang}")
	@Operation(summary = "반복정보조회", description = "추가 관광정보 상세내역을 조회한다. 상세반복정보를 안내URL의 국문관광정보 상세 매뉴얼 문서를 참고하시기 바랍니다.")
	public ResponseEntity<Map<String,Object>> detailInfo1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ " \"contentId\": \"2674675\",\r\n"
					+ " \"contentTypeId\" : \"15\"\r\n"
					+ "}" , description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "contentId	콘텐츠ID	필수<br/>"
							+ "contentTypeId	관광타입 ID	필수<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailIntro1(lang,requestParam);
	}
	
	@PostMapping("/detailImage1/{lang}")
	@Operation(summary = "이미지정보조회", description = "관광정보에 매핑되는 서브이미지목록 및 이미지 자작권 공공누리유형을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> detailImage1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ " \"contentId\": \"2674675\",\r\n"
					+ " \"imageYN\" : \"Y\",\r\n"
					+ " \"subImageYN\" : \"Y\"\r\n"
					+ "}" , description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "contentId	콘텐츠ID	필수<br/>"
							+ "imageYN	이미지조회필수	선택<br/>"
							+ "subImageYN	이미지조회2	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailImage1(lang,requestParam);
	}
	
	@PostMapping("/areaBasedSyncList1/{lang}")
	@Operation(summary = "관광정보 동기화 목록 조회", description = "지역기반 관광정보파라미터 타입에 따라서 제목순,수정일순,등록일순 정렬검색목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> areaBasedSyncList1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ " \"showflag\" : \"1\",\r\n"
					+ " \"modifiedtime\" : \"20210406\",\r\n"
					+ " \"listYN\" : \"Y\",\r\n"
					+ " \"arrange\" : \"A\",\r\n"
					+ " \"contentTypeId\" : \"\",\r\n"
					+ " \"areaCode\" : \"\",\r\n"
					+ " \"sigunguCode\" : \"\",\r\n"
					+ " \"cat1\" : \"\",\r\n"
					+ " \"cat2\" : \"\",\r\n"
					+ " \"cat3\" : \"\"\r\n"
					+ "}" , description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "showflag	표출여부	선택<br/>"
							+ "modifiedtime	수정일	선택<br/>"
							+ "listYN	목록 구분	선택<br/>"
							+ "arrange	정렬 구분	선택<br/>"
							+ "contentTypeId	관광타입 ID	선택<br/>"
							+ "areaCode	지역코드	선택<br/>"
							+ "sigunguCode	시군구코드	선택<br/>"
							+ "cat필수	대분류	선택<br/>"
							+ "cat2	중분류	선택<br/>"
							+ "cat3	소분류	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.areaBasedSyncList1(lang,requestParam);
	}
	
	@PostMapping("/areaCode1/{lang}")
	@Operation(summary = "지역코드조회", description = "지역코드목록을 지역,시군구,읍면동 코드목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> areaCode1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
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
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.areaCode1(lang,requestParam);
	}
	
	@PostMapping("/detailPetTour1/{lang}")
	@Operation(summary = "반려동물 동반 여행 정보", description = "타입별 반려동물 동반 여행 정보를 조회하는 기능입니다.")
	public ResponseEntity<Map<String,Object>> detailPetTour1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ " \"contentId\": 125694\r\n"
					+ "}" , description = "numOfRows	한 페이지 결과 수	선택<br/>"
							+ "pageNo	페이지 번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키 (서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "contentid	콘텐츠ID	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailPetTour1(lang,requestParam);
	}
	
	@PostMapping("/categoryCode1/{lang}")
	@Operation(summary = "서비스분류코드조회", description = "서비스분류코드목록을 대,중,소분류로 조회하는 기능")
	public ResponseEntity<Map<String,Object>> categoryCode1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스 키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ " \"contentTypeId\" : \"12\",\r\n"
					+ " \"cat1\" : \"\",\r\n"
					+ " \"cat2\" : \"\",\r\n"
					+ " \"cat3\" : \"\"\r\n"
					+ "}" , description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "contentTypeId	관광타입 ID	선택<br/>"
							+ "cat필수	대분류	선택<br/>"
							+ "cat2	중분류	선택<br/>"
							+ "cat3	소분류	선택<br/>" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.categoryCode1(lang,requestParam);
	}
	
	@PostMapping("/areaBasedList1/{lang}")
	@Operation(summary = "지역기반 관광정보조회", description = "지역기반 관광정보파라미터 타입에 따라서 제목순,수정일순,등록일순 정렬검색목록을 조회하는 기능")
	public ResponseEntity<Map<String,Object>> areaBasedList1(
			@Parameter(name = "lang", description = "언어 지정", in = ParameterIn.PATH)
			@PathVariable String lang,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "요청 파라미터 예시", required = true, content = @Content(schema = @Schema(type = "object", implementation = Map.class),   examples = @ExampleObject(name = "요청 Object Json", value = "{\r\n"
					+ "\"numOfRows\" :  \"10\",\r\n"
					+ "\"pageNo\" : \"1\",\r\n"
					+ "\"MobileOS\" : \"ETC\",\r\n"
					+ "\"MobileApp\" : \"AppTest\" , \r\n"
					+ "\"serviceKey\" : \"서비스키\",\r\n"
					+ "\"_type\" : \"json\",\r\n"
					+ " \"listYN\" : \"Y\",\r\n"
					+ " \"arrange\" : \"A\",\r\n"
					+ " \"contentTypeId\" : \"32\",\r\n"
					+ " \"areaCode\" : \"4\",\r\n"
					+ " \"sigunguCode\" : \"4\",\r\n"
					+ " \"cat1\" : \"B02\",\r\n"
					+ " \"cat2\" : \"B0201\",\r\n"
					+ " \"cat3\" : \"B02010100\",\r\n"
					+ " \"modifiedtime\" : \"\"\r\n"
					+ "}" , description = "numOfRows	한페이지결과수	선택<br/>"
							+ "pageNo	페이지번호	선택<br/>"
							+ "MobileOS	OS 구분	필수<br/>"
							+ "MobileApp	서비스명	필수<br/>"
							+ "serviceKey	인증키(서비스키)	필수<br/>"
							+ "_type	응답메세지 형식	선택<br/>"
							+ "listYN	목록구분	선택<br/>"
							+ "arrange	정렬구분	선택<br/>"
							+ "contentTypeId	관광타입 ID	선택<br/>"
							+ "areaCode	지역코드	선택<br/>"
							+ "sigunguCode	시군구코드	선택<br/>"
							+ "cat필수	대분류	선택<br/>"
							+ "cat2	중분류	선택<br/>"
							+ "cat3	소분류	선택<br/>"
							+ "modifiedtime	수정일	선택<br/>"
							+ "" )))
			@RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.areaBasedList1(lang,requestParam);
	}
	

 
}
