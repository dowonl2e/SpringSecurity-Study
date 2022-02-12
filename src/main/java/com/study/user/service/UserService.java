package com.study.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.user.dto.UserRequestDTO;
import com.study.user.dto.UserResponseDTO;
import com.study.user.entity.User;
import com.study.user.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Transactional
	public UserResponseDTO save(UserRequestDTO params) {
		User user = userRepository.save(params.toEntity());
		return new UserResponseDTO(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).orElse(null);
	}
	
}

