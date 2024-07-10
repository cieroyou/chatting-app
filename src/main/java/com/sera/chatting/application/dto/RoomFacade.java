package com.sera.chatting.application.dto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomFacade {
	/**
	 * 방 생성 후 방 id를 반환
	 */
	public CreateRoomResponse createRoom(String roomName) {

		String roomId = "absdj-room";
		return new CreateRoomResponse(roomId);
	}

	/**
	 * 방 입장 요청
	 */
	public void joinRoom(String roomId, String userId) {

	}
}
