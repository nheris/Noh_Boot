package com.winter.app.transfer.card;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

//공통로직
//설정하겟다
@Component
@Aspect
@Slf4j
public class Card {
	
	//언제실행?
	@Around("execution(* com.winter.app.transfer.*.*(..))")
	public Object checkCard(ProceedingJoinPoint joinPoint) throws Throwable {//메서드(getBus)를 객체화해서 joinPoint에 담음
		System.out.println(" Before card check");
		
		log.info("========{}",joinPoint.getArgs());
		
		Object obj = joinPoint.proceed();//여기서 그 메서드 실행
		
		log.info("========{}========",obj);
		
		System.out.println(" After card check");
		
		return obj;
	}
}
