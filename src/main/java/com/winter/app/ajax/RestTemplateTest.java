package com.winter.app.ajax;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class RestTemplateTest {
	
	//inner클래스 -> java GUI
	
	public void flux() throws Exception{
		//WebClient 객체 생성
		WebClient webClient = WebClient.builder()
								.baseUrl("https://jsonplaceholder.typicode.com/")
								.build();
		int num=1;
		//요청 정보 설정
//		Mono<ResponseEntity<RestVO>> response = webClient.get()
//														 .uri("/posts/1")//baseUrl 이후 들어갈 주소
//														 .retrieve()//응답
//														 .toEntity(RestVO.class);//어떤 타입으로 받을거냐
//		RestVO restVO = response.block().getBody();
		
//		Mono<RestVO> response = webClient.get()
//				 .uri("/posts/1")
//				 .retrieve()
//				 .bodyToMono(RestVO.class);
//		RestVO restVO = response.block();
		
		//여러개받아올때
		Flux<RestVO> response = webClient.get()
				 .uri("/posts")
				 .retrieve()
				 .bodyToFlux(RestVO.class);
		response.subscribe((r)->{
			RestVO restVO = r;
			log.info("{}",restVO);
		});
		
		
	}
	
	public void rest() throws Exception{
		log.info("RestTemplate");
		RestTemplate restTemplate = new RestTemplate();
		//헤더
		//파라미터 현재 없음
		
		//요청객체만들기
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(null, null);
		//요청 전송 결과 처리  \타입
		//ResponseEntity<RestVO> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", RestVO.class);
		ResponseEntity<List> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", List.class);
		
		List<RestVO> result = response.getBody();
		
		log.info("{}",result);
		
		
		
	}
}
