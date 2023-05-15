package com.nadeul.ndj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping("/test")
	public String index() {
		System.out.println("testsets");
		return "index";
	}
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess() {
		System.out.println("testsets");
		return "loginSuccess";
	}

}
