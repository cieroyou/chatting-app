package com.sera.chatting.common.converter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sera.chatting.common.exception.SocketMessageParsingException;
import com.sera.chatting.presentation.websocket.dto.CreateRoomRequest;
import com.sera.chatting.presentation.websocket.dto.common.RequestBody;
import com.sera.chatting.presentation.websocket.dto.common.RequestMessage;

@ExtendWith(MockitoExtension.class)
class RequestMessageConverterTest {
	@InjectMocks
	private RequestMessageConverter converter;
	@Mock
	private WebSocketSession session;
	private final ObjectMapper objectMapper = new ObjectMapper();

	private static final String TRANSACTION_ID = "12345";
	private static final String CREATE_ROOM_COMMAND = "create_room";
	private static final CreateRoomRequest CREATE_ROOM_REQUEST = CreateRoomRequest.createRoomRequestBuilder()
		.command("create_room")
		.roomName("Test Room")
		.description("A room for testing")
		.maxParticipants(10)
		.build();

	private static final String INVALID_MESSAGE = "{\"transaction_id\":\"12345\", \"type\":\"invalid\", \"body\":{\"command\":\"create_room\"}}";

	private String getSocketMessage(RequestBody body, String sessionId) throws JsonProcessingException {
		RequestMessage message = new RequestMessage(TRANSACTION_ID, sessionId, body, Instant.now());
		return objectMapper.writeValueAsString(message);
	}

	@BeforeEach
	void setUp() throws JsonProcessingException {
		this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		this.objectMapper.registerModule(new JavaTimeModule());
		converter = new RequestMessageConverter(objectMapper);
	}

	@Test()
	@DisplayName("유효한 Json 문자열메세지가 성공적으로 RequestMessage로 변환된다")
	void convertToRequestMessage_Success() throws JsonProcessingException {
		String sessionId = "shsjhdf2";
		String validCreateRoomRequestMessage = getSocketMessage(CREATE_ROOM_REQUEST, sessionId);
		// when
		when(session.getId()).thenReturn(sessionId);
		RequestMessage result = converter.convertToRequestMessage(session, validCreateRoomRequestMessage);
		RequestBody body = result.getBody();
		// then
		assertNotNull(result);
		assertInstanceOf(CreateRoomRequest.class, body);
		CreateRoomRequest createRoomRequest = (CreateRoomRequest)body;
		assertEquals(TRANSACTION_ID, result.getTransactionId());
		assertEquals(CREATE_ROOM_COMMAND, body.getCommand());
		assertEquals(CREATE_ROOM_REQUEST.getRoomName(), createRoomRequest.getRoomName());
		assertEquals(CREATE_ROOM_REQUEST.getDescription(), createRoomRequest.getDescription());
		assertEquals(CREATE_ROOM_REQUEST.getMaxParticipants(), createRoomRequest.getMaxParticipants());
	}

	@Test
	@DisplayName("Exception is thrown for invalid message type")
	void convertToRequestMessage_InvalidMessageType_throwsSocketMessageParsingException() {
		assertThrows(SocketMessageParsingException.class,
			() -> converter.convertToRequestMessage(session, INVALID_MESSAGE));
	}

	@Test
	@DisplayName("Exception is thrown when transaction_id is missing")
	void convertToRequestMessage_MissingTransactionId_throwsSocketMessageParsingException() {
		String jsonMessage = "{\"type\":\"request\", \"body\":{\"command\":\"create_room\"}}";
		assertThrows(SocketMessageParsingException.class,
			() -> converter.convertToRequestMessage(session, jsonMessage));
	}

	@Test
	@DisplayName("Exception is thrown when command is missing in body")
	void convertToRequestMessage_MissingCommand_throwsSocketMessageParsingException() {
		String jsonMessage = "{\"transaction_id\":\"12345\", \"type\":\"request\", \"body\":{}}";
		assertThrows(SocketMessageParsingException.class,
			() -> converter.convertToRequestMessage(session, jsonMessage));
	}

	@Test
	@DisplayName("Exception is thrown for unsupported command")
	void convertToRequestMessage_UnsupportedCommand_throwsSocketMessageParsingException() {
		String jsonMessage = "{\"transaction_id\":\"12345\", \"type\":\"request\", \"body\":{\"command\":\"unsupported_command\"}}";
		assertThrows(SocketMessageParsingException.class,
			() -> converter.convertToRequestMessage(session, jsonMessage));
	}

}
