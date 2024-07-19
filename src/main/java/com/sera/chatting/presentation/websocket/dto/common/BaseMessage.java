package com.sera.chatting.presentation.websocket.dto.common;

import java.time.Instant;

public interface BaseMessage {
	String getTransactionId();

	MessageType getType();

	Instant getTimestamp();
}
