package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.winter.app.member.MemberService;

@Configuration //xml파일이당
@EnableWebSecurity //기본말고 내걸쓰겟당
public class SecurityConfig {
	
	@Autowired
	private SecurityLoginSucessHandler handler;
	@Autowired
	private SecurityFailHandler handler2;
	@Autowired
	private MemberService memberService;
	
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
						//.defaultSuccessUrl("/")//로그인성공시 어디로 갈지
						.successHandler(handler)//로그인 성공 후 추가 작업하고싶을 때 위에거랑 둘중 하나밖에 못씀
						//.failureUrl("notice/list")//실패했을때 어디로 갈지
						.failureHandler(handler2)
//						//파라미터이름이 password이 아닌 'password'를 사용 했을 경우
//						.passwordParameter("pw")//다른이름으로 name지정해뒀다면
//						//파라미터이름이 username이 아닌 'id'를 사용 했을 경우
//						.usernameParam  eter("id")
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
		.rememberMe((rememberMe)->rememberMe
						.rememberMeParameter("rememberMe")	//파라미터명
						.tokenValiditySeconds(60)	//remember-me token의 유효 시간 (Cookie) , 초단위
						.key("rememberMe")//키 필수 암거나알아서
						.userDetailsService(memberService)//인증 절차를 진행할 UserDetailService, 필수
						.authenticationSuccessHandler(handler) //Login이 성공했을 때 실행할 Handler
						//.userSecureCookie(false)
		//로그아웃안하고 창나갔다가 다시 들어왔을때 로그인 되어있는지 확인
				
		)					//변수명 그냥 s로 해도됨
		.sessionManagement((sessionManagement)->sessionManagement
						.maximumSessions(1)       //최대 허용 가능한 session 수, -1 이면 무제한
						.maxSessionsPreventsLogin(false) //false - 이전 사용자 세션만료 true - 현재 접속 하려는 사용자 인증 실패 
						.expiredUrl("/expired")         //세션이 만료되었을 경우 리다이렉트 할 페이지
		//동시접속 해보기..안되네...
		)
		
		
		;
		
		
		return security.build();
	}
	
}
