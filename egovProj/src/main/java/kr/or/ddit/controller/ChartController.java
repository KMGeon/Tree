package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/chart")
public class ChartController {
	// 요청 url : /chart/chart01
	// json 구글차트

	@GetMapping("/chart01")
	public String chart01() {
		return "chart/chart01";
	}

	// 요청 url : /chart/chart01Multi
	// json 구글 멀티 차트
	@GetMapping("/chart01Multi")
	public String chart01Multi() {
		return "chart/chart01Multi";
	}

	// 요청 url : /chart/chart02
	// oracle 연동해서 구글차트
	@GetMapping("/chart02")
	public String chart02() {
		return "chart/chart02";
	}
}