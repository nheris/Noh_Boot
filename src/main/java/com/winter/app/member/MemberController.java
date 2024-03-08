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

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("add")
	public void add(@ModelAttribute MemberVO memberVO) throws Exception{
		
		//model.addAttribute("memberVO",memberVO) 랑 같음
		
	}
	@PostMapping("add")		//검증하고 컨트롤러들어옴			//검증 된걸 담음???못들음
	public String add(@Valid MemberVO memberVO, BindingResult bindingResult,Model model) throws Exception{
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
}
