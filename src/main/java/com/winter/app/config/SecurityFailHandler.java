package com.winter.app.config;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("로그인 실패 확인 ==> {}", exception);
		
		String message="";
		
		if(exception instanceof BadCredentialsException) {
			message="비밀번호를 확인";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
			message="아이디가 없다";
		}
		
		if(exception instanceof AccountExpiredException) {
			message="계정 유효기간 만료";
		}
		
		if(exception instanceof LockedException) {
			message="계정 잠김";
		}
		if(exception instanceof CredentialsExpiredException) {
			message="비밀번호 유효기간 만료";
		}
		if(exception instanceof DisabledException) {
			message="휴면 계정";
		}
		
		/* Encoding 문제 발생시 */
		message = URLEncoder.encode(message, "UTF-8");
	

		//Foward Redirect
		//1. Redirect
		response.sendRedirect("/member/login?message="+message);
		//2. Foward 노션봐...
//		request.setAttribute("message", message);
//		request.setAttribute("memberVO", new MemberVO());
//		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
	}
	
}
