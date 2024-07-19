package com.sera.chatting.infrastructure.dataaccess;

import org.springframework.stereotype.Component;

import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.domain.ChattingRoom;
import com.sera.chatting.infrastructure.interfaces.ChattingRoomReader;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChattingRoomReaderImpl implements ChattingRoomReader {
	private final ChattingRoomRepository repository;

	@Override
	public ChattingRoom readByChattingRoomId(RoomId roomId) {
		return repository.findByChattingRoomId(roomId)
			.orElseThrow(() -> new EntityNotFoundException("ChattingRoom not found, chattingRoomId: " + roomId));
	}
}
