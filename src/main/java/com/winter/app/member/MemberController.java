package com.winter.app.member;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.member.group.MemberJoinGroup;
import com.winter.app.member.group.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String login(@ModelAttribute MemberVO memberVO, HttpSession session) throws Exception {
		
		//로그인 성공 후 뒤로 가기 처리(member/login, add도 나중처리 )
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");//속성명 값 넣기
		
		
		if(obj == null){
			return "member/login";
		}
		
		SecurityContextImpl contextImpl = (SecurityContextImpl) obj;
		String user = contextImpl.getAuthentication().getPrincipal().toString();
		
		
		if(user.equals("anonymousUser")) {
			return "member/login";
		}
				
				
		return "redirect:/";
	}
	
	@GetMapping("add")
	public void add(@ModelAttribute MemberVO memberVO) throws Exception{
		
		//model.addAttribute("memberVO",memberVO) 랑 같음
		
	}
	@PostMapping("add")	//검증할 그룹명 지정	//검증하고 컨트롤러들어옴(@Valid)		//검증 된걸 담음???못들음
	public String add(@Validated(MemberJoinGroup.class) MemberVO memberVO, BindingResult bindingResult,Model model) throws Exception{
		//log.info("Member Add");
		
		boolean check = memberService.checkMember(memberVO, bindingResult);
		if(check) {
			return "member/add";
		}
		
		int result = memberService.add(memberVO);
		model.addAttribute("result","member.add.result");
		model.addAttribute("path","/");
		
		//service로 보냄
		return "commons/result";
	}
	
	@GetMapping("update")
	public void update(Model model) throws Exception{
//		MemberVO memberVO = memberService.detail();
//		model.addAttribute("memberVO", memberVO);//속성명 생략시 변수명이 속성명 됨.근데 안될 때 있어 집어넣는게 안전
		
	}
	@PostMapping("update")			//검증 										//검증결과 받는거 
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult)throws Exception{
			//검증실패시
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		return "redirect:../";
	}
	
	@GetMapping("page")
	public void page(HttpSession session) throws Exception{
		//로그인 한 사용자정보 조회 
		//속성명 알아오기 Attribute name
		//Session 이용
		//이터레이트??전버전??
		Enumeration<String> en = session.getAttributeNames();
	
		while(en.hasMoreElements()) {
					//========= attribute SPRING_SECURITY_CONTEXT
			log.info("========= attribute {}", en.nextElement());
		}
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("=== obj {}", obj);
		
		//impl implement?
		SecurityContextImpl contextImpl = (SecurityContextImpl) obj;
		String name = contextImpl.getAuthentication().getName();
		MemberVO memberVO = (MemberVO)contextImpl.getAuthentication().getPrincipal();
		
		log.info("=== Name {}", name);
		log.info("=== MemberVO {}", memberVO);
		
		//세선받거나 SecurityContextHolder 이용
		SecurityContext context = SecurityContextHolder.getContext();
		name = context.getAuthentication().getName();
		
		
	}	
}
