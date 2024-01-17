package com.sera.chatting.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 400
    BAD_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST, 400);

    private final String errorMsg;
    private final HttpStatus httpStatus;
    private final int code;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}
