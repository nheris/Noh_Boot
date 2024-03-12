package com.winter.app.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.winter.app.member.group.MemberJoinGroup;
import com.winter.app.member.group.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
								//Spring Security에서 제공하는 VO개념, Interface
public class MemberVO implements UserDetails {
	
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
	
	
	/* DB에서 조회시 사용자 권한을 담을 List */
	private List<RoleVO> roleVOs;
	
	@Override// 검증할떄 메서드 실행? 	//
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/* 사용자 권한을 Security에서 사용 할 수 있도록 변환 */
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO:roleVOs) {
			GrantedAuthority g = new SimpleGrantedAuthority(roleVO.getRoleName());
	      	authorities.add(g);
	      }
		
		return authorities;
	}
	
//	@Override
//	public boolean isAccountNonExpired() {
//		// 만효되지않음?
//		return true;
//	}
//	
//	@Override			//잠겨져있지않음?
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//	
//	@Override		//비번만료됨?
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//	
//	@Override	//계정사용가능?
//	public boolean isEnabled() {
//		return true;
//	}
	
	private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
	
}
