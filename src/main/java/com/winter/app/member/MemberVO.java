package com.winter.app.member;

import com.winter.app.member.group.MemberJoinGroup;
import com.winter.app.member.group.MemberUpdateGroup;

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
										//그룹 지정(Join,update시 검증)
	@NotBlank(message = "필수 입력", groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
	private String username;
	
					//Join시만 검증
	@NotBlank(groups = MemberJoinGroup.class)
	@Size(min = 8, max = 16, groups = MemberJoinGroup.class)
	private String password;
	
	private String passwordCheck;
	
	//@Pattern(regexp = "")정규표현식
	private String phone;
	
	@Email(groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
	private String email;
	
	private String address;
	
	private String name;
	
}
