package com.sera.chatting.common.converter;

import java.time.Instant;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.chatting.common.exception.SocketMessageParsingException;
import com.sera.chatting.presentation.websocket.dto.CreateRoomRequest;
import com.sera.chatting.presentation.websocket.dto.JoinRoomRequest;
import com.sera.chatting.presentation.websocket.dto.common.RequestBody;
import com.sera.chatting.presentation.websocket.dto.common.RequestMessage;

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
		SocketMessageParsingException {
		JsonNode rootNode;
		String transactionId;
		RequestBody body;
		try {
			rootNode = objectMapper.readTree(jsonMessage);
			transactionId = rootNode.path(TRANSACTION_ID).asText();
			JsonNode bodyNode = rootNode.path(BODY);

			validateMessage(rootNode);

			String command = bodyNode.path(COMMAND).asText();
			body = getRequestBody(bodyNode, command);
		} catch (JsonProcessingException e) {
			throw new SocketMessageParsingException("Failed to parse Json String message, message: {}", jsonMessage);
		}

		return new RequestMessage(transactionId, body, Instant.now());
	}

	private void validateMessage(JsonNode rootNode) {
		String type = rootNode.path(TYPE).asText();
		JsonNode bodyNode = rootNode.path(BODY);

		if (!REQUEST.equals(type)) {
			throw new SocketMessageParsingException("Invalid message type: {}", type);
		}
		if (!rootNode.has(TRANSACTION_ID)) {
			throw new SocketMessageParsingException("Missing transaction_id in message");
		}
		if (!bodyNode.has(COMMAND)) {
			throw new SocketMessageParsingException("Missing command in body");
		}
	}

	private RequestBody getRequestBody(JsonNode bodyNode, String command) {
		RequestBody body;
		try {
			switch (command) {
				case "create_room" -> body = objectMapper.treeToValue(bodyNode, CreateRoomRequest.class);
				case "join_room" -> body = objectMapper.treeToValue(bodyNode, JoinRoomRequest.class);
				default -> throw new IllegalArgumentException("Unsupported command: " + command);
			}
		} catch (Exception e) {
			throw new SocketMessageParsingException("Failed to parse body to RequestBody");
		}
		assert body != null;
		body.setCommand(command);
		return body;
	}
}
