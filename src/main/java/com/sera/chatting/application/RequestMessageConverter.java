package com.sera.chatting.application;

import java.time.Instant;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.chatting.application.dto.RequestBody;
import com.sera.chatting.application.dto.RequestMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * MessageConverter는 텍스트 형식의 메시지를 RequestMessage DTO로 변환하는 역할을 합니다.
 * 이 클래스는 애플리케이션 로직의 일부로서, 데이터 변환을 담당합니다. 따라서 MessageConverter도 application 패키지 아래에 위치합니다.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RequestMessageConverter {
	private final ObjectMapper objectMapper;

	private final String TRANSACTION_ID = "transaction_id";
	private final String COMMAND = "command";
	private final String REQUEST = "request";
	private final String TYPE = "type";
	private final String BODY = "body";

	public RequestMessage convertToRequestMessage(WebSocketSession session, String jsonMessage) throws
		JsonProcessingException {
		JsonNode rootNode = objectMapper.readTree(jsonMessage);
		String transactionId = rootNode.path(TRANSACTION_ID).asText();
		Instant timestamp = Instant.now();
		String type = rootNode.path(TYPE).asText();
		JsonNode bodyNode = rootNode.path(BODY);

		if (!type.equals(REQUEST)) {
			log.error("Invalid message type: {}", type);
			throw new IllegalArgumentException("Invalid message type");
		}
		if (bodyNode.isMissingNode()) {
			log.error("Missing body in message");
			throw new IllegalArgumentException("Missing body in message");
		}
		if (!bodyNode.has(COMMAND)) {
			log.error("Missing command in body");
			throw new IllegalArgumentException("Missing command in body");
		}
		String command = bodyNode.path(COMMAND).asText();
		RequestBody body = new RequestBody(command);
		return new RequestMessage(transactionId, body, timestamp);
	}
}
