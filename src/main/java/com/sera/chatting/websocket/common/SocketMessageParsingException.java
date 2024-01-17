package com.sera.chatting.websocket.common;

import com.sera.chatting.common.exception.BaseException;
import com.sera.chatting.common.exception.ErrorCode;

public class SocketMessageParsingException extends BaseException {
    public SocketMessageParsingException() {
        super(ErrorCode.BAD_REQUEST);
    }

    public SocketMessageParsingException(String message) {
        super(message, ErrorCode.BAD_REQUEST);
    }

    public SocketMessageParsingException(String field, String message) {
        super(String.format("%s: %s", field, message), ErrorCode.BAD_REQUEST);
    }
}
