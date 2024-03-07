package com.winter.app.board;

import java.util.List;

import com.winter.app.util.Pager;

public interface BoardDAO {
	//메서드명 = 매퍼 id, 리턴타입으로
	public Long getTotalCount(Pager pager) throws Exception;
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	public int add(BoardVO boardVO) throws Exception;
	
	public int addFile(FileVO fileVO) throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO) throws Exception;
	
	public FileVO getFileDetail(FileVO fileVO)throws Exception;
}
