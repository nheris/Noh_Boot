package com.winter.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winter.app.lambda.TestInterface;
import com.winter.app.member.MemberVO;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//@RestController
@Controller
@Slf4j //여기서 바로 로그찍을 수 있게 해줌, log변수 만들어짐
public class TestController {
	

	
	@GetMapping("/")
	public String test() {
		
		//람다는 JS와 비슷 funtion(){} : () -> {}
		//JAVA ()->{}
		
		//인터페이스 타입으론 선언가능
		TestInterface ti = (int a, int b)-> a+b;
		
		Supplier<MemberVO> s = ()-> new MemberVO();//꺼내고 싶은 인터페이스 있을 때
		MemberVO memberVO = s.get();
		
		
		System.out.println(ti.t1(3, 2));
		//System.out.println(ti.t1(3, 2));
		
//		TestInterface t2 = new TestInterface() {
//			
//			@Override
//			public int t1(int n1, int n2) {
//				return 0;
//			}
//		};
		
		
		//trace, debug, info, warn, error 젤 윗단계 
		/*
		 * log.error("Error message"); log.warn("warn message");
		 * log.info("info message");//레벨일 info일때 찍음 log.debug("Debug message");
		 * log.trace("Trace message");
		 */
		
		
		return "index";
	}
	
	@GetMapping("/expired")
	public String expired(Model model) {
		model.addAttribute("result", "로그아웃되었습니다.");
		model.addAttribute("path", "/");
		
		
		return "commons/result";
	}
	
}
