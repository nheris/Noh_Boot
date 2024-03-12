package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class) //트랜잭션 처리
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	//@Qualifier("ps")//빈이름 ps인거 
	private PasswordEncoder passwordEncoder;
	//add 검증 메서드 사용자 정의
	//비번일치, id 중복 여부
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		
		log.info("==========로그인 진행");
		log.info("----------- {} -------", username);
		
		try {
			memberVO= memberDAO.getDetail(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memberVO;
	}
	
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
		//평문 password 암호화
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.add(memberVO);
		
		//회원의 Role 정보 저장
		result = memberDAO.addMemberRole(memberVO);
		return result;
	}
	
	//삭제할 메서드
	public MemberVO detail() throws Exception{
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("winter");
		
		return memberDAO.getDetail(memberVO);
	}
}
