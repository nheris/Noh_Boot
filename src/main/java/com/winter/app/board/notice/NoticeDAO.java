package com.winter.app.board.notice;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;

@Mapper
//메서드명 = 매퍼 id, 리턴타입으로
public interface NoticeDAO extends BoardDAO{
}
