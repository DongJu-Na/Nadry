package com.nadeul.ndj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeul.ndj.dto.AuthenticationRequest;
import com.nadeul.ndj.dto.AuthenticationResponse;
import com.nadeul.ndj.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/v1/oauth")
@RequiredArgsConstructor
@RestController
public class KakaoApiController {
	
	private final AuthenticationService service;
		// 임시 인증/인가 로직 필요
	  @PostMapping("/authenticate")
	  public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest request ) {
	    return ResponseEntity.ok(service.authenticate(request));
	  }

}
