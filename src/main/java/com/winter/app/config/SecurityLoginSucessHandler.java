package com.winter.app.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginSucessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//log.info("Login 성공 했을 때 실행");
		//authentication.getPrincipal();//MemberVO

		String rememberId = request.getParameter("rememberId");
		
		if(rememberId != null) {
			//log.info("ID를 저장");
			//꺼낸 ID를 Client에 cookie에 저장
			log.info(authentication.getName());
			//서버쪽에서 쿠키 만들기
			Cookie cookie = new Cookie("rememberId", authentication.getName());
			//쿠키 최대기간
			cookie.setMaxAge(600);//초단위 10분
			//어느 경로에서 쓸건지
			cookie.setPath("/");//서브도메인에서도 사용가능
			
			//응답에 쿠키담아 보내기
			response.addCookie(cookie);
		}else {
			//log.info("ID를 저장하지 않기");
			//쿠키지우기
			Cookie [] cookie = request.getCookies();
			
			for(Cookie c: cookie) {
				if(c.getName().equals("rememberId")) {
					//클라이언트
					c.setMaxAge(0);
					c.setValue("");
					c.setPath("/");
					//쿠키 다시보내기
					response.addCookie(c);
					log.info("Cookie 삭제하기");
					break;
				}
			}
			
			
		}
		
		
		
		
		response.sendRedirect("/");
		
	}
	
}
