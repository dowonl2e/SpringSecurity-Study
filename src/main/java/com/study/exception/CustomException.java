package com.study.exception;

import com.study.values.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
	
	private final ErrorCode errorCode;
	
}
