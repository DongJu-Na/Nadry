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
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.locationBasedList1(lang,requestParam);
	}
	
	@PostMapping("/searchKeyword1")
	public ResponseEntity<Map<String,Object>> searchKeyword1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.searchKeyword1(lang,requestParam);
	}
	
	@PostMapping("/searchFestival1")
	public ResponseEntity<Map<String,Object>> searchFestival1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.searchFestival1(lang,requestParam);
	}
	
	@PostMapping("/searchStay1")
	public ResponseEntity<Map<String,Object>> searchStay1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.searchStay1(lang,requestParam);
	}
	
	@PostMapping("/detailCommon1")
	public ResponseEntity<Map<String,Object>> detailCommon1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailCommon1(lang,requestParam);
	}
	
	@PostMapping("/detailIntro1")
	public ResponseEntity<Map<String,Object>> detailIntro1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailIntro1(lang,requestParam);
	}
	
	@PostMapping("/detailInfo1")
	public ResponseEntity<Map<String,Object>> detailInfo1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailIntro1(lang,requestParam);
	}
	
	@PostMapping("/detailImage1")
	public ResponseEntity<Map<String,Object>> detailImage1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailImage1(lang,requestParam);
	}
	
	@PostMapping("/areaBasedSyncList1")
	public ResponseEntity<Map<String,Object>> areaBasedSyncList1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.areaBasedSyncList1(lang,requestParam);
	}
	
	@PostMapping("/areaCode1")
	public ResponseEntity<Map<String,Object>> areaCode1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.areaCode1(lang,requestParam);
	}
	
	@PostMapping("/detailPetTour1")
	public ResponseEntity<Map<String,Object>> detailPetTour1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.detailPetTour1(lang,requestParam);
	}
	
	@PostMapping("/categoryCode1")
	public ResponseEntity<Map<String,Object>> categoryCode1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.categoryCode1(lang,requestParam);
	}
	
	@PostMapping("/areaBasedList1")
	public ResponseEntity<Map<String,Object>> areaBasedList1(@PathVariable String lang, @RequestBody Map<String, Object> requestParam) throws Exception {
		if(lang == null || lang.equals("")) {
			lang = "kor";
		}
		return korService1ApiService.areaBasedList1(lang,requestParam);
	}
	

 
}
