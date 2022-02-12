package com.study.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private String name;
	private String celno;
	private char resignYn = 'N';
	
	private String roles;
	
	private LocalDateTime createdDate = LocalDateTime.now();
	
	private LocalDateTime modifiedDate;
	
	private LocalDateTime lastLoginDate;
	
	private LocalDateTime resignDate;

	@Builder
	public User(String email, String name, String password, String celno, String roles) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.celno = celno;
		this.roles = roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		if(!ObjectUtils.isEmpty(roles)) {
			String[] rolearr = roles.split(",");
			for(String role : rolearr) {
				collectors.add(new SimpleGrantedAuthority(role));
			}
		}
		
		return collectors;
		/*
		return this.roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		*/
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
