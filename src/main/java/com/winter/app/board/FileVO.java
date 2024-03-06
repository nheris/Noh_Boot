package com.winter.app.board;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FileVO {
	private Long fileNum;
	private Long boardNum;
	private String fileName;
	private String oriName;
}
