package com.study.user.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.provider.JwtTokenProvider;
import com.study.response.BasicResponse;
import com.study.response.CommonResponse;
import com.study.response.ErrorResponse;
import com.study.user.dto.UserRequestDTO;
import com.study.user.dto.UserResponseDTO;
import com.study.user.entity.User;
import com.study.user.service.UserService;
import com.study.values.ErrorCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	
	@PostMapping(value = "/signup")
	public UserResponseDTO signUp(@RequestBody final UserRequestDTO params) {
		params.setPassword(passwordEncoder.encode(params.getPassword()));
		params.setRoles("USER");
		return userService.save(params);
	}
	
	@PostMapping(value = "/signin")
	public ResponseEntity<? extends BasicResponse> signIn(@RequestBody final UserRequestDTO params) {
		Optional<User> user = Optional.ofNullable((User)userService.loadUserByUsername(params.getEmail()));
		
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
					.body(new ErrorResponse(
							ErrorCode.USER_NOT_FOUND.getCode(),
							ErrorCode.USER_NOT_FOUND.getMessage()));
		}
		else if(!passwordEncoder.matches(params.getPassword(), user.get().getPassword())) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
					.body(new ErrorResponse(
							ErrorCode.USER_PWD_NOT_MATCHED.getCode(),
							ErrorCode.USER_PWD_NOT_MATCHED.getMessage()));
		}
		
		CommonResponse<UserResponseDTO> response = new CommonResponse<UserResponseDTO>(
				new UserResponseDTO(user.get(), jwtTokenProvider.createToken(user.get().getEmail()))
		);
		
		return ResponseEntity.ok().body(response);
	}
}
