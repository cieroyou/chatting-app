package com.sera.chatting.application.dto;

import org.springframework.stereotype.Service;

import com.sera.chatting.domain.ChattingRoom;
import com.sera.chatting.infrastructure.interfaces.ChattingRoomStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChattingRoomFacade {

	private final ChattingRoomStore roomStore;

	/**
	 * 방 생성 후 방 id를 반환
	 */
	public CreateRoomResponse createRoom(String roomName) {
		var room = ChattingRoom.createRoom(roomName, "");
		roomStore.save(room);
		return new CreateRoomResponse(room.getChattingRoomId());
	}

	/**
	 * 방 입장 요청
	 */
	public void joinRoom(String roomId, String userId) {

	}
}
