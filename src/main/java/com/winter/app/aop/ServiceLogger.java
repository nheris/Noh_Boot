package com.winter.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect ////Aspect 역할을 할 클래스를 선언하기 위해 어노테이션 선언
@Slf4j
public class ServiceLogger {
	
	//공통로직 Advice
	@Around("execution( * com.winter.app.**.*Service.*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
		
		//ProceedingJoinPoint 인터페이스의 제공 메서드 메서드 
		//Signature getSignature() : 호출되는 메서드에 대한 정보를 구한다. 
		//Object getTarget()  : 대상 객체를 구한다. 
		//Object[] getArgs() : 파라미터의 목록을 구한다. 
		
		log.info("============= Service 실행 ==============");
		log.info("======= 매개변수 : {} ", joinPoint.getArgs());
		
		Object obj = joinPoint.proceed();
		
		
		log.info("======= 리턴값 : {}", obj);
		
		return obj;
		
	}
}
