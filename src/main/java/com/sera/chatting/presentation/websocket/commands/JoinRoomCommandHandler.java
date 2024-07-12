package com.sera.chatting.presentation.websocket.commands;

import org.springframework.stereotype.Component;

import com.sera.chatting.application.ChattingRoomFacade;
import com.sera.chatting.application.dto.JoinRoomRequest;
import com.sera.chatting.application.dto.common.AckBody;
import com.sera.chatting.application.dto.common.AckData;
import com.sera.chatting.common.CommandHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JoinRoomCommandHandler implements CommandHandler<JoinRoomRequest, AckData> {
	private final ChattingRoomFacade chattingRoomFacade;

	@Override
	public boolean supports(String command) {
		return "join_room".equals(command);
	}

	@Override
	public AckBody<AckData> handleCommand(String sessionId, JoinRoomRequest request) {
		// Assume payload is roomId;userId for simplicity
		// String[] parts = payload.split(";");
		// chattingRoomFacade.joinRoom(parts[0], parts[1]);
		return AckBody.success();
	}
}
