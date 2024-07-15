package com.sera.chatting.presentation.websocket.commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sera.chatting.application.ChattingRoomFacade;
import com.sera.chatting.application.CreateRoomResponse;
import com.sera.chatting.application.dto.CreateRoomRequest;
import com.sera.chatting.application.dto.common.AckBody;

@ExtendWith(MockitoExtension.class)
class CreateRoomCommandHandlerTest {
	@Mock
	private ChattingRoomFacade chattingRoomFacade;
	@InjectMocks
	private CreateRoomCommandHandler createRoomCommandHandler;

	private static final String CREATE_COMMAND = "create_room";
	private static final String WRONG_COMMAND = "delete_room";

	@DisplayName("supports 메소드는 올바른 커맨드에 대해 true를 반환한다")
	@Test
	void supports_ShouldReturnTrue_ForCorrectCommand() {
		assertTrue(createRoomCommandHandler.supports(CREATE_COMMAND),
			"supports should return true for 'create_room' command");
	}

	@DisplayName("supports 메소드는 잘못된 커맨드에 대해 false를 반환한다")
	@Test
	void supports_ShouldReturnFalse_ForIncorrectCommand() {
		assertFalse(createRoomCommandHandler.supports(WRONG_COMMAND),
			"supports should return false for commands other than 'create_room'");
	}

	@DisplayName("유효한방생성요청이_들어왔을때_성공응답을_반환하고,방생성로직을_수행한다")
	@Test
	void handleCommand_ShouldReturnSuccessAckBody_WhenCalledWithValidRequest() {
		// given
		String expectedRoomName = "Test Room";
		String expectedRoomId = "123";
		int expectedMaxParticipants = 10;
		String expectedDescription = "Test Description";
		AckBody.Result expectedResult = AckBody.Result.SUCCESS;
		CreateRoomRequest request = CreateRoomRequest.createRoomRequestBuilder()
			.roomName(expectedRoomName)
			.maxParticipants(expectedMaxParticipants)
			.description(expectedDescription)
			.build();
		CreateRoomResponse expectedResponse = new CreateRoomResponse(expectedRoomId);

		// Mock behavior
		when(chattingRoomFacade.createRoom(anyString(), anyInt(), anyString())).thenReturn(expectedResponse);

		// when
		AckBody<CreateRoomResponse> result = createRoomCommandHandler.handleCommand("sessionId", request);

		// then
		assertEquals(expectedResponse, result.getData(), "The response data should match the expected response");
		assertEquals(expectedResult, result.getResult());
		verify(chattingRoomFacade).createRoom(expectedRoomName, expectedMaxParticipants, expectedDescription);
	}
}