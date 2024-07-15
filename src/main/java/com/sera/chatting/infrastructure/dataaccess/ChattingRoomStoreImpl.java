package com.sera.chatting.infrastructure.dataaccess;

import org.springframework.stereotype.Component;

import com.sera.chatting.domain.ChattingRoom;
import com.sera.chatting.infrastructure.interfaces.ChattingRoomStore;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ChattingRoomStoreImpl implements ChattingRoomStore {
	private final ChattingRoomRepository chattingRoomRepository;

	@Override
	public ChattingRoom save(ChattingRoom room) {
		return chattingRoomRepository.save(room);
	}
}
