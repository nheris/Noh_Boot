package com.winter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
						//Security 기능 해제 (react)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
public class NohBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(NohBootApplication.class, args);
	}

}
