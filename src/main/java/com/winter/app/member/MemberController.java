package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.member.group.MemberJoinGroup;
import com.winter.app.member.group.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String login(@ModelAttribute MemberVO memberVO) throws Exception {
		return "member/login";
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
	
}
