package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController extends HttpServlet {

	// 메인 화면
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// About Us 이동
	@GetMapping("/mvIntro")
	public String mvintro() {
		return "intro/intro";
	}
	
	// 사이트맵 이동
	@GetMapping("/mvSitemap")
	public String mvSitemap() {
		return "sitemap/sitemap";
	}

}
