package com.winter.app.board.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.util.Pager;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@Value("${board.notice.name}")//{프로터피스 키}, 클래스 롬북말고 
	private String name;
	
	@ModelAttribute("board")
	public String board() {
		return this.name;
	}
	
	@GetMapping("list/{page}")
	@CrossOrigin
	public Map<String, Object> gerList(@PathVariable Long page) throws Exception{
		log.info("================{}================",name);
		Pager pager = new Pager();
		pager.setPage(page);
		
		List<BoardVO> ar = noticeService.getList(pager);
		
		log.info("Page : {}", pager.getStartIndex());
		log.info("Page : {}", pager.getPerPage());
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", ar);
		map.put("pager", pager);
		
		return map;
	}
	
	@GetMapping("add")
	public String add() throws Exception{
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(HttpSession session, NoticeVO noticeVO, MultipartFile [] attach) throws Exception{
		
		if(session.getAttribute("member") != null) {
		
			int result = noticeService.add(noticeVO, attach);
		}
		
		return "redirect:./list";

	}
	
	@GetMapping("detail")
	public String getDetail(BoardVO boardVO, Model model) throws Exception{
		boardVO = noticeService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);
		
		return "board/detail";
	}
	
	@GetMapping("fileDown")
	public String fileDown(FileVO fileVO, Model model)throws Exception{
		fileVO = noticeService.getFileDetail(fileVO);

		model.addAttribute("fileVO", fileVO);

		return "fileDownView";
	}
}
