package com.sera.chatting.api.usecase;

import org.springframework.stereotype.Service;

import com.sera.chatting.application.dto.RoomCommand;
import com.sera.chatting.domain.ChattingRoom;
import com.sera.chatting.infrastructure.dataaccess.ChattingRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChattingRoomUsecase {

	private final ChattingRoomRepository chattingRoomRepository;

	/**
	 * 채팅방 생성 후 채팅방 아이디 반환
	 *
	 * @return 채팅방 아이디
	 */
	public String createRoom(RoomCommand.CreateRoom createRoom) {
		var room = ChattingRoom.createRoom(createRoom.name(), createRoom.maxParticipants(), createRoom.description());
		chattingRoomRepository.save(room);
		return room.getChattingRoomId();
	}
}
