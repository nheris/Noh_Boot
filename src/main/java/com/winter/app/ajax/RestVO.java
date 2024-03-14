package com.winter.app.ajax;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class RestVO {
	private String userId;
	
	private Long id;
	
	private String title;
	private String body;
	
	
}
