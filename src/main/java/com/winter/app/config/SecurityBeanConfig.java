package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeanConfig {
//	The dependencies of some of the beans in the application context form a cycle:
//
//		┌─────┐
//		|  securityConfig (field private com.winter.app.member.MemberService com.winter.app.config.SecurityConfig.memberService)
//		↑     ↓
//		|  memberService (field private org.springframework.security.crypto.password.PasswordEncoder com.winter.app.member.MemberService.passwordEncoder)
//		└─────┘
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();//암호화 해주는 객체 (특히 비번)
	}
}
