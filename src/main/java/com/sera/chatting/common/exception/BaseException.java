package com.sera.chatting.common.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;

    // TODO 로그 찍을 때 필요할지 고민해보기
//    private Long requesterId;
//    private String messageType;

    public BaseException() {
    }


    public BaseException(ErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getErrorMsg();
    }

    public BaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public BaseException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }
}