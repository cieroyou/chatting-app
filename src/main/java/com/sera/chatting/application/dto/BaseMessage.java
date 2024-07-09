package com.sera.chatting.application.dto;

import java.time.Instant;

public interface BaseMessage {
	String getTransactionId();

	String getType();

	Instant getTimestamp();
}
