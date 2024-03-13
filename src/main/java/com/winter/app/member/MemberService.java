package com.winter.app.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class) //트랜잭션 처리
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService{
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	//@Qualifier("ps")//빈이름 ps인거 
	private PasswordEncoder passwordEncoder;
	
	
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
			check=true;					//[필드,변수 명]    [에러 코드]    [메세지 내용]
			//bindingResult.rejectValue("멤버변수명(path)", "properties의key(코드)");
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
	
	
	
	//UserDetailService
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
	
	
	//DefaultOAuth2UserService
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("kakao =====> {}", userRequest);//org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest@20f1aa
		
		ClientRegistration c = userRequest.getClientRegistration();
		
		log.info("ClientId =====> {}",c.getClientId());	//eef~
		log.info("ClientName =====> {}",c.getClientName()); //Kakao
		
		OAuth2User user = super.loadUser(userRequest);
		log.info("@@@ {}", user); //
		log.info("@@@ {}", user.getName()); //
		log.info("@@@ {}", user.getAuthorities()); //[OAUTH2_USER, SCOPE_profile_image, SCOPE_profile_nickname]
		
		log.info("Property : {}", user.getAttribute("properties").toString());
		
		if(c.getClientName().equals("Kakao")) {
			user = this.kakao(user);
			
		}
		
		return user; 
	}
	
	private OAuth2User kakao(OAuth2User oAuth2User) {
		Map<String,Object> map = oAuth2User.getAttribute("properties");
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(oAuth2User.getName());
		memberVO.setName(map.get("nickname").toString());
		memberVO.setAttributes(oAuth2User.getAttributes());
		
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");//지금은 걍 집어넣음. 나중엔 디비에 멤버롤 넣기
		list.add(roleVO);
		
		memberVO.setRoleVOs(list);
		
		return memberVO;
	}
	
}
