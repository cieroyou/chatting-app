package com.sera.chatting.presentation.websocket.dto.common;

import java.time.Instant;

import lombok.Getter;

@Getter
public class EventMessage implements BaseMessage {
	private final String transactionId;
	private final String type = "event"; // Not final anymore, to allow flexibility if needed
	private final EventBody body;
	private final Instant timestamp;

	// Constructor
	public EventMessage(String transactionId, EventBody body) {
		this.transactionId = transactionId;
		this.body = body;
		this.timestamp = Instant.now();
	}

	@Override
	public String getTransactionId() {
		return this.transactionId;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public Instant getTimestamp() {
		return this.timestamp;
	}
}