package com.winter.app.board;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data //@Setter@Getter 합친거
//@AllArgsConstructor //모든 매개변수있는 생성자. BoardVO(Long, String, String, String, Date, Long)만들어줌
//@NoArgsConstructor//매개변수없는 기본생성자 만들어줌
public class BoardVO {
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date boardDate;
	private Long boardHit;
	
	private List<FileVO> fileVOs;
}
