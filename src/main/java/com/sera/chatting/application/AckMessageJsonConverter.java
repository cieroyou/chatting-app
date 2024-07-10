package com.sera.chatting.application;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.chatting.application.dto.AckMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AckMessageJsonConverter {
	private final ObjectMapper objectMapper;

	public String convertToJson(AckMessage ackMessage) {
		try {
			return objectMapper.writeValueAsString(ackMessage);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert AckMessage to JSON", e);
		}
	}
}
