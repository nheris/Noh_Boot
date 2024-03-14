package com.winter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.ajax.RestTemplateTest;

import lombok.extern.slf4j.Slf4j;

//@RestController
@Controller
@Slf4j //여기서 바로 로그찍을 수 있게 해줌, log변수 만들어짐
public class TestController {
	
	@Autowired
	private RestTemplateTest template;
	
	@GetMapping("/")
	public String test() throws Exception{
		//trace, debug, info, warn, error 젤 윗단계 
		/*
		 * log.error("Error message"); log.warn("warn message");
		 * log.info("info message");//레벨일 info일때 찍음 log.debug("Debug message");
		 * log.trace("Trace message");
		 */
		//template.rest();
		template.flux();
		
		return "index";
	}
	
	@GetMapping("/expired")
	public String expired(Model model) {
		model.addAttribute("result", "로그아웃되었습니다.");
		model.addAttribute("path", "/");
		
		
		return "commons/result";
	}
	
}
