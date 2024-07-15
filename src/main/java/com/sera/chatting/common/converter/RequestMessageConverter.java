package com.sera.chatting.common.converter;

import java.time.Instant;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.chatting.application.dto.CreateRoomRequest;
import com.sera.chatting.application.dto.JoinRoomRequest;
import com.sera.chatting.application.dto.common.RequestBody;
import com.sera.chatting.application.dto.common.RequestMessage;

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

	private static final String TRANSACTION_ID = "transaction_id";
	private static final String COMMAND = "command";
	private static final String TYPE = "type";
	private static final String BODY = "body";
	private static final String REQUEST = "request";

	public RequestMessage convertToRequestMessage(WebSocketSession session, String jsonMessage) throws
		JsonProcessingException {
		JsonNode rootNode = objectMapper.readTree(jsonMessage);
		String transactionId = rootNode.path(TRANSACTION_ID).asText();
		Instant timestamp = Instant.now();
		JsonNode bodyNode = rootNode.path(BODY);

		validateMessage(rootNode);

		String command = bodyNode.path(COMMAND).asText();
		RequestBody body = getRequestBody(bodyNode, command);
		return new RequestMessage(transactionId, body, timestamp);
	}

	private void validateMessage(JsonNode rootNode) {
		String type = rootNode.path(TYPE).asText();
		JsonNode bodyNode = rootNode.path(BODY);
		if (!REQUEST.equals(type)) {
			log.error("Invalid message type: {}", type);
			throw new IllegalArgumentException("Invalid message type");
		}

		if (rootNode.isMissingNode()) {
			log.error("Missing transaction_id in message");
			throw new IllegalArgumentException("Missing transaction_id in message");
		}
		if (!bodyNode.has(COMMAND)) {
			log.error("Missing command in body");
			throw new IllegalArgumentException("Missing command in body");
		}
	}

	private RequestBody getRequestBody(JsonNode bodyNode, String command) throws JsonProcessingException {
		RequestBody body = null;
		switch (command) {
			case "create_room" -> body = objectMapper.treeToValue(bodyNode, CreateRoomRequest.class);
			case "join_room" -> body = objectMapper.treeToValue(bodyNode, JoinRoomRequest.class);
			default -> throw new IllegalArgumentException("Unsupported command: " + command);
		}
		assert body != null;
		body.setCommand(command);
		return body;
	}
}
