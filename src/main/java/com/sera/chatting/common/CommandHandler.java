package com.sera.chatting.common;

import com.sera.chatting.application.dto.common.AckBody;
import com.sera.chatting.application.dto.common.AckData;
import com.sera.chatting.application.dto.common.RequestBody;

public interface CommandHandler<T extends RequestBody, R extends AckData> {

	boolean supports(String command);

	AckBody<R> handleCommand(String sessionId, T request);
}
