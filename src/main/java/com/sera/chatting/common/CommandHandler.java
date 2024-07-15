package com.sera.chatting.common;

import com.sera.chatting.presentation.websocket.dto.common.AckBody;
import com.sera.chatting.presentation.websocket.dto.common.AckData;
import com.sera.chatting.presentation.websocket.dto.common.RequestBody;

public interface CommandHandler<T extends RequestBody, R extends AckData> {

	boolean supports(String command);

	AckBody<R> handleCommand(String sessionId, T request);
}
