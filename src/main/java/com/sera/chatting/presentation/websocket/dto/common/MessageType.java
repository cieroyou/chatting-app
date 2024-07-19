package com.sera.chatting.presentation.websocket.dto.common;

import lombok.Getter;

@Getter
public enum MessageType {
	ACK("ack"), REQUEST("request"), EVENT("event");

	private final String value;

	MessageType(String value) {
		this.value = value;
	}

	public static MessageType fromValue(String value) {
		// value 소문자변환
		value = value.toLowerCase().trim();
		for (MessageType messageType : MessageType.values()) {
			if (messageType.value.equals(value)) {
				return messageType;
			}
		}
		throw new IllegalArgumentException("Invalid value: " + value);
	}
}
