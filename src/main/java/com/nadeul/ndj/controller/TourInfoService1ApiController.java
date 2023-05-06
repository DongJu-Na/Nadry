package com.nadeul.ndj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.service.TourInfoService1ApiService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DONGJU
 * 
 */
@Slf4j
@RequestMapping("/api/v1/tour")
@RestController
public class TourInfoService1ApiController {
	
	@Autowired
	TourInfoService1ApiService korService1ApiService;
	
	@PostMapping("/locationBasedList1/{lang}")
	public ResponseEntity<Map<String,Object>> locationBasedList1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null) {
			lang = "kor";
		}
		return korService1ApiService.locationBasedList1(lang,requestParam);
	}
	
	@PostMapping("/searchKeyword1")
	public ResponseEntity<Map<String,Object>> searchKeyword1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.searchKeyword1(requestParam);
	}
	
	@PostMapping("/searchFestival1")
	public ResponseEntity<Map<String,Object>> searchFestival1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.searchFestival1(requestParam);
	}
	
	@PostMapping("/searchStay1")
	public ResponseEntity<Map<String,Object>> searchStay1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.searchStay1(requestParam);
	}
	
	@PostMapping("/detailCommon1")
	public ResponseEntity<Map<String,Object>> detailCommon1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.detailCommon1(requestParam);
	}
	
	@PostMapping("/detailIntro1")
	public ResponseEntity<Map<String,Object>> detailIntro1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.detailIntro1(requestParam);
	}
	
	@PostMapping("/detailInfo1")
	public ResponseEntity<Map<String,Object>> detailInfo1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.detailIntro1(requestParam);
	}
	
	@PostMapping("/detailImage1")
	public ResponseEntity<Map<String,Object>> detailImage1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.detailImage1(requestParam);
	}
	
	@PostMapping("/areaBasedSyncList1")
	public ResponseEntity<Map<String,Object>> areaBasedSyncList1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.areaBasedSyncList1(requestParam);
	}
	
	@PostMapping("/areaCode1")
	public ResponseEntity<Map<String,Object>> areaCode1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.areaCode1(requestParam);
	}
	
	@PostMapping("/detailPetTour1")
	public ResponseEntity<Map<String,Object>> detailPetTour1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.detailPetTour1(requestParam);
	}
	
	@PostMapping("/categoryCode1")
	public ResponseEntity<Map<String,Object>> categoryCode1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.categoryCode1(requestParam);
	}
	
	@PostMapping("/areaBasedList1")
	public ResponseEntity<Map<String,Object>> areaBasedList1(@RequestBody Map<String, Object> requestParam) throws Exception {
		return korService1ApiService.areaBasedList1(requestParam);
	}
	

 
}
