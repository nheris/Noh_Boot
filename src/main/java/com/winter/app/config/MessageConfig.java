package com.winter.app.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration // xml설정파일이당
public class MessageConfig implements WebMvcConfigurer {
	
	@Bean //객체만듦(xml대신) 뭐쓸건지(resolver)  빈에 등록
	LocaleResolver localeResolver() {
		//session
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN); //첨시작할때 지역 뭐로 설정?
		
		//cookie
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		cResolver.setDefaultLocale(Locale.KOREAN);
		//쿠키형식 (키 밸루) 키 뭘로 쓸건지 설정
		//cResolver.setCookieName("lang"); //deprecated
		
		
		//둘중하나
		return cResolver;
	}
	
	//Message Interceptor 객체 생성
	@Bean //첫글자 소문자 빈이름 @Bean("")->이름 지정
	LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		
		return changeInterceptor;
	}
	
	// 같은클래스 에서 할시 ?????
	//인터셉터 등록하는 메서드(addInterceptors) or InterceptorConfig에 객체생성하거나 (첫문ㄴ자 소문자)
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addInterceptor(changeInterceptor()).addinet;
//	} 
		
	
	
	
}
