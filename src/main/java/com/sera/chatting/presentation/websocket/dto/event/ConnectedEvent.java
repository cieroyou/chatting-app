package com.sera.chatting.presentation.websocket.dto.event;

import java.io.Serial;
import java.io.Serializable;

import com.sera.chatting.presentation.websocket.dto.common.EventBody;

import lombok.Getter;

@Getter
public class ConnectedEvent extends EventBody implements Serializable {
	@Serial
	private static final long serialVersionUID = 5L; // 직렬화 버전 ID

	private static final String event = "connected";
	private final String sessionId;

	public ConnectedEvent(String sessionId) {
		super(event);
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "ConnectedEvent{" +
			"sessionId='" + sessionId + '\'' +
			"} " + super.toString();
	}

	// public RoomCommand.CreateRoom toCommand() {
	// 	return new RoomCommand.CreateRoom(roomName, description, maxParticipants);
	// }
}