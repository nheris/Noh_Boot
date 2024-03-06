package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;
import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void getListTest() throws Exception{
		Pager pager = new Pager();
		pager.setPage(1L);
		pager.makeIndex();
		
		Long totalCount = noticeDAO.getTotalCount(pager);
		pager.makeNum(totalCount);
		
		//info(STring)이라 {}안에 ,변수 들어감
		log.info("Pager {}",pager);
		
		List<BoardVO> ar = noticeDAO.getList(pager);
		assertEquals(10, ar.size());
	}
	
	//@Test
	void addTest() throws Exception {
		for(int i=0;i<120;i++) {
			NoticeVO noticeVO =new NoticeVO();
			noticeVO.setBoardWriter("tester");
			noticeVO.setBoardTitle("Test Title");
			noticeVO.setBoardContents("Test Contents"+i);
			int result = noticeDAO.add(noticeVO);
			//assertEquals(1, result);
			
		}
		
		System.out.println("finish");
	}

}
