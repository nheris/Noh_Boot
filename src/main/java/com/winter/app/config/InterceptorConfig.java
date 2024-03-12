package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.app.interceptors.LoginInterceptor;
import com.winter.app.interceptors.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private TestInterceptor testInterceptor;
	@Autowired
	private LoginInterceptor loginInterceptor;
	@Autowired
	private LocaleChangeInterceptor locatChangeInterceptor;
	//LocaleChangeInterceptor : 로케일을 변경하는 별도의 컨트롤러 클래스를 구현할 필요 없이 메시지를 해당 언어로 변경 가능
	
	
	@Override	//인터셉터 등록하는 메서드
	public void addInterceptors(InterceptorRegistry registry) {
		// 어떤 url이 왔을때 어떤 Interceptor를 거치게 할것인가
		// config class에 등록된 순서대로 적용
		//등록 적용할 Interceptor
		/*						
		 * registry.addInterceptor(testInterceptor) .addPathPatterns("/notice/**")	//적용할 URL 등록
		 * .excludePathPatterns("/notice/add"); //exclude							//제외할 URL등록
		 * 
		 * registry.addInterceptor(loginInterceptor) .addPathPatterns("/**")
		 * .excludePathPatterns("");
		 */
		registry.addInterceptor(locatChangeInterceptor)
				.addPathPatterns("/**");
				
		
	}
}
