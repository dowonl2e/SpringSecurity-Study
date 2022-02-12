package com.study.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse extends BasicResponse {

	private final String errorCode;
	private final String errorMessage;
	
	
}
