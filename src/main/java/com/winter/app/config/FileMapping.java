package com.winter.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration //***-context.xml	//
public class FileMapping implements WebMvcConfigurer{
	
	@Value("${app.upload.url}")
	private String urlPath; //  /files/**  요청들러오면 컨트롤러 가지말고 filePath 주소에서 찾기
	@Value("${app.upload.base}")
	private String filePath; //  D://upload/
	//ex)files/notice/파일명 -> D://upload/notice/파일명 으로 가서 찾음
	
	
	@Override  //addResourceHandlers는 리소스 등록 및 핸들러를 관리하는 객체인 ResourceHandlerRegistry를 통해 리소스 위치와 이 리소스와 매칭될 url을 등록
	//URL과 Local 연결
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//<resources mapping="/resources/**" location="/resources/" />
		registry.addResourceHandler(urlPath)   //어느 경로로 들어왔을때 매핑이 되어줄 것인지를 정의
				.addResourceLocations(filePath); //실제 파일이 있는 경로를 지정
	}
}
