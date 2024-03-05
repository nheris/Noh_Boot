package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.intThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.x509;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;

@SpringBootTest
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void getListTest() throws Exception{
		List<BoardVO> ar = noticeDAO.getList();
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
