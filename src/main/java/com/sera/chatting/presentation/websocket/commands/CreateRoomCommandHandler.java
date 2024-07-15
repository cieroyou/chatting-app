package com.sera.chatting.presentation.websocket.commands;

import org.springframework.stereotype.Component;

import com.sera.chatting.application.ChattingRoomFacade;
import com.sera.chatting.application.CreateRoomResponse;
import com.sera.chatting.application.dto.CreateRoomRequest;
import com.sera.chatting.application.dto.common.AckBody;
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
		var response = chattingRoomFacade.createRoom(request.getRoomName(), request.getMaxParticipants(),
			request.getDescription());
		return AckBody.success(response);
	}
}
