package com.sera.chatting.common.converter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.chatting.presentation.websocket.dto.common.EventMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventMessageJsonConverter {
	private final ObjectMapper objectMapper;

	public String convertToJson(EventMessage eventMessage) {
		try {
			return objectMapper.writeValueAsString(eventMessage);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert EventMessage to JSON", e);
		}
	}
}
