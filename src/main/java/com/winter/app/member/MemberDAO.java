package com.winter.app.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {	//MemberDAO를 MemberMapper라고 하기도 함
	public MemberVO getDetail(MemberVO memberVO) throws Exception;
	
	public int add(MemberVO memberVO)throws Exception;
	
	public int update(MemberVO memberVO)throws Exception;
}
