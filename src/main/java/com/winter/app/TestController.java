package com.winter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

//@RestController
@Controller
@Slf4j //여기서 바로 로그찍을 수 있게 해줌, log변수 만들어짐
public class TestController {
	

	
	@GetMapping("/")
	public String test() {
		//trace, debug, info, warn, error 젤 윗단계 
		/*
		 * log.error("Error message"); log.warn("warn message");
		 * log.info("info message");//레벨일 info일때 찍음 log.debug("Debug message");
		 * log.trace("Trace message");
		 */
		
		
		return "index";
	}
	
}
