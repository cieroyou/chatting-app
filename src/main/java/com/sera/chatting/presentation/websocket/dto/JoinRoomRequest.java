package com.sera.chatting.presentation.websocket.dto;

import java.io.Serial;
import java.io.Serializable;

import com.sera.chatting.presentation.websocket.dto.common.RequestBody;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JoinRoomRequest extends RequestBody implements Serializable {

	@Serial
	private static final long serialVersionUID = 3L; // 직렬화 버전 ID
	String roomId;
	String userId;

	public JoinRoomRequest(String roomId, String userId) {
		this.roomId = roomId;
		this.userId = userId;
	}
}
