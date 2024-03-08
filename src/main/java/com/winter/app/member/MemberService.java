package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	//add 검증 메서드 사용자 정의
	//비번일치, id 중복 여부
	
	public boolean checkMember(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean check=false;
		//check 가 true 라면 에러가 있다.
		//check 가 false 라면 에러 없음.
		
		//어노테이션 검증 결과
		check= bindingResult.hasErrors();
		
		//비번 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())){
			check=true;
			bindingResult.rejectValue("passwordCheck", "memberVO.password.equals");
		}
		//id중복
		MemberVO result = memberDAO.getDetail(memberVO);
		
		if(result != null) {
			check=true;
			bindingResult.rejectValue("username", "memberVO.username.equals");
		}
		
		return check;
		
	}
	
	public int add(MemberVO memberVO)throws Exception{
		return memberDAO.add(memberVO);
	}
}
