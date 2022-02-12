package com.study.user.dto;

import com.study.user.entity.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDTO {
	
	private String email;
	private String password;
	private String name;
	private String celno;
	private String roles;
	
	public User toEntity() {
		return User.builder()
				.email(email)
				.password(password)
				.name(name)
				.celno(celno)
				.roles(roles)
				.build();
	}
	
}
