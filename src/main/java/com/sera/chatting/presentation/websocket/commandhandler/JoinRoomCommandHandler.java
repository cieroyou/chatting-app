package com.sera.chatting.presentation.websocket.commandhandler;

import org.springframework.stereotype.Component;

import com.sera.chatting.application.ChattingRoomFacade;
import com.sera.chatting.common.CommandHandler;
import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.common.domain.valueobject.UserId;
import com.sera.chatting.presentation.websocket.dto.JoinRoomRequest;
import com.sera.chatting.presentation.websocket.dto.common.AckBody;
import com.sera.chatting.presentation.websocket.dto.common.AckData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JoinRoomCommandHandler implements CommandHandler<JoinRoomRequest, AckData> {
	private final ChattingRoomFacade chattingRoomFacade;
	private static final String COMMAND = "join_room";

	@Override
	public boolean supports(String command) {
		return COMMAND.equals(command);
	}

	@Override
	public AckBody<AckData> handleCommand(String sessionId, JoinRoomRequest request) {
		chattingRoomFacade.addParticipant(
			new RoomId(request.getRoomId()),
			new UserId(request.getUserId()));
		return AckBody.success();
	}
}
