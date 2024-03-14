package com.winter.app.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.member.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler{
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminkey;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {		
		//log.info("Logout Success Handler");
		MemberVO memberVO = (MemberVO) authentication.getPrincipal();
		
		//일반유저
		if(memberVO.getSocial() == null) {
			response.sendRedirect("/");
			return;
		}
		
		//카카오 로그아웃
		if(memberVO.getSocial().toUpperCase().equals("KAKAO")) {
			//log.info("카카오 사용자가 맞다.");
			
			MultiValueMap<String, String> p = new LinkedMultiValueMap<>();
			//target id 타입
			p.add("target_id_type", "user_id");
			p.add("target_id", memberVO.getUsername());
			
			//객체생성
			WebClient webClient = WebClient.create("https://kapi.kakao.com/v1/user/logout");
			Mono<String> result = webClient.post()
					 .header("Authorization", "KakaoAK "+adminkey)
					 //post flux parameter //WebClient Post 요청
					 .body(BodyInserters.fromFormData(p))
					 .retrieve()
					 .bodyToMono(String.class)//문서..
					 ;
			
			log.info("kakako Logout {}", result.block());
			
			response.sendRedirect("/");
		}
		
		//네이버 로그아웃
		
		
		
		
	}
}
