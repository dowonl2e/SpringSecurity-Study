package com.study.user.dto;

import java.time.LocalDateTime;

import com.study.user.entity.User;

import lombok.Getter;

@Getter
public class UserResponseDTO {
	
	private Long idx;
	private String email;
	private String name;
	private String celno;
	private char resignYn;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private LocalDateTime lastLoginDate;
	private LocalDateTime resignDate;
	
	private String token;

	public UserResponseDTO(User entity) {
		this.idx = entity.getIdx();
		this.email = entity.getEmail();
		this.name = entity.getName();
		this.celno = entity.getCelno();
		this.resignYn = entity.getResignYn();
		this.createdDate = entity.getCreatedDate();
		this.modifiedDate = entity.getModifiedDate();
		this.lastLoginDate = entity.getLastLoginDate();
		this.resignDate = entity.getResignDate();
	}
	
	public UserResponseDTO(User entity, String token) {
		this.idx = entity.getIdx();
		this.email = entity.getEmail();
		this.name = entity.getName();
		this.celno = entity.getCelno();
		this.resignYn = entity.getResignYn();
		this.createdDate = entity.getCreatedDate();
		this.modifiedDate = entity.getModifiedDate();
		this.lastLoginDate = entity.getLastLoginDate();
		this.resignDate = entity.getResignDate();
		this.token = token;
	}
	
}
