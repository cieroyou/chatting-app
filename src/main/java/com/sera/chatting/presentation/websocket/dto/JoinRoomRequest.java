package com.sera.chatting.presentation.websocket.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import com.sera.chatting.presentation.websocket.dto.common.RequestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JoinRoomRequest extends RequestBody implements Serializable {

	@Serial
	private static final long serialVersionUID = 3L; // 직렬화 버전 ID
	UUID roomId;
	UUID userId;

	public JoinRoomRequest(UUID roomId, UUID userId) {
		this.roomId = roomId;
		this.userId = userId;
	}
}
