package com.winter.app.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberVO {
	@NotBlank(message = "필수 입력")
	private String username;
	@NotBlank
	@Size(min = 8, max = 16)
	private String password;
	
	private String passwordCheck;
	
	//@Pattern(regexp = "")정규표현식
	private String phone;
	@Email
	private String email;
	
	private String address;
	
	private String name;
	
}
