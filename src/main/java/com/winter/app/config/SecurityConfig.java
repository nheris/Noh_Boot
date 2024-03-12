package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //xml파일이당
@EnableWebSecurity //기본말고 내걸쓰겟당
public class SecurityConfig {
	
	//지정자 디폴트로
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.ignoring()	//시큐리티 거치지말고 통과
				.requestMatchers("/css/**")
				.requestMatchers("/js/**")
				.requestMatchers("/vendor/**")
				.requestMatchers("/img/**")
				.requestMatchers("/favicon/**");
	}
	
	@Bean
	SecurityFilterChain filterChain (HttpSecurity security) throws Exception{
//		security.cors()
//				.and()
//				.csrf()
//				.disable();
		
				//권한 관련된 설정
		security.authorizeHttpRequests(
				(authorizeHttpRequests) -> authorizeHttpRequests
											.requestMatchers("/").permitAll()//누구나 들어올수있게
											.requestMatchers("/member/add").permitAll()
											.requestMatchers("/member/page", "/member/logout").authenticated()
											.requestMatchers("/notice/list").authenticated()//로그인한사람만 들어감
											.requestMatchers("/notice/add", "/notice/delete").hasRole("ADMIN")//관리자만
											.requestMatchers("/notice/update").hasAnyRole("ADMIN", "MANAGER")
											//.anyRequest().authenticated()//이외나머지 전부 로그인만 한사람
											.anyRequest().permitAll()
											//authorizeHttpRequests 끝부분
				
		)//사용자로그인폼 쓸거임
		.formLogin(
				(login)->login	//로그인페이지 url
						.loginPage("/member/login")
						.defaultSuccessUrl("/")//로그인성공시 어디로 갈지
//						//파라미터이름이 password이 아닌 'password'를 사용 했을 경우
//						.passwordParameter("pw")//다른이름으로 name지정해뒀다면
//						//파라미터이름이 username이 아닌 'id'를 사용 했을 경우
//						.usernameParameter("id")
						.permitAll()//이주소 전부 허용
							
		)
		.logout(
				(logout)->logout
						//.logoutUrl("/member/logout")//어떤 url발생시 로그아웃
						.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
						.logoutSuccessUrl("/") //성공했을때 어디로 갈건지
						.invalidateHttpSession(true)//로그아웃시 session만료
						.permitAll()
			)
		
		;
		
		
		return security.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();//암호화 해주는 객체 (특히 비번)
	}
}
