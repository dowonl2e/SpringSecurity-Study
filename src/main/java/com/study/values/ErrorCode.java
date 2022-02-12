package com.study.values;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	
	//패턴
	//HttpStatus.code + isValid(0:valid / 1:invalid) + 사용자별 패턴 
	
	//회원 관련 에러코드
	/*
	 * 사용자별 패턴
	 * 1. 00 : 회원 정보
	 * 2. 01 : 이메일
	 * 3. 02 : 비밀번호
	 */
	USER_JOIN_FAIL("400100", "회원가입에 실패했습니다."),
	USER_NOT_FOUND("412100", "일치하는 회원 정보가 없습니다."),
	USER_EMAIL_NOT_FOUND("412101", "일치하는 이메일 정보가 없습니다."),
	USER_PWD_NOT_MATCHED("412102", "비밀번호가 틀렸습니다.");
	
	private final String code;
	private final String message;
	
}
