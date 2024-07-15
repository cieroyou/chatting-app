package com.sera.chatting.presentation.websocket.commandhandler;

import org.springframework.stereotype.Component;

import com.sera.chatting.application.ChattingRoomFacade;
import com.sera.chatting.presentation.websocket.dto.CreateRoomResponse;
import com.sera.chatting.presentation.websocket.dto.CreateRoomRequest;
import com.sera.chatting.presentation.websocket.dto.common.AckBody;
import com.sera.chatting.common.CommandHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CreateRoomCommandHandler implements CommandHandler<CreateRoomRequest, CreateRoomResponse> {

	private final ChattingRoomFacade chattingRoomFacade;
	private static final String COMMAND = "create_room";

	@Override
	public boolean supports(String command) {
		return COMMAND.equals(command);
	}

	@Override
	public AckBody<CreateRoomResponse> handleCommand(String sessionId, CreateRoomRequest request) {
		var response = chattingRoomFacade.createRoom(request.toCommand());
		return AckBody.success(response);
	}
}
