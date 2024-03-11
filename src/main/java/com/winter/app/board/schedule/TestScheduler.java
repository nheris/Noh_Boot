package com.winter.app.board.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberDAO;

//@Component
public class TestScheduler {
	//@Autowired
	private MemberDAO memberDAO;
	
	//고정(일정) 간격으로 반복 실행 , 반복주기 1000 = 1초마다 / 서버실행되고나서 처음 언제할거냐
	//@Scheduled(fixedRate = 1000, initialDelay = 2000)
	//@Scheduled(fixedRateString = "1000", initialDelayString = "2000")
	public void useFixRate() {
		System.out.println("Fix Rate");
		
	}
	//해당 메서드가 종료 후 일정 간격 실행 
	//@Scheduled(fixedDelay = 2000, initialDelayString = "2000")
	public void useFixDelay() {
		System.out.println("FixDelay");
		
	}
	
	//@Scheduled(cron = "30 * * * * *") 주로 사용
	public void useCron() {
		System.out.println("Cron");
		//memberDAO.getDetail(null)
		
	}
}
