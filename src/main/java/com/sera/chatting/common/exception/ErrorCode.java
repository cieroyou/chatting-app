package com.sera.chatting.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	// 400 - client-side Error
	BAD_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST, 400),
	ALREADY_A_PARTICIPANT("이미 참여한 방입니다.", HttpStatus.BAD_REQUEST, 400),
	ROOM_FULL("방이 가득 찼습니다.", HttpStatus.BAD_REQUEST, 400);

	private final String errorMsg;
	private final HttpStatus httpStatus;
	private final int code;

	public String getErrorMsg(Object... arg) {
		return String.format(errorMsg, arg);
	}
}
