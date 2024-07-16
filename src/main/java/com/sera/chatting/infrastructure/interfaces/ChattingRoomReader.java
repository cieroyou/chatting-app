package com.sera.chatting.infrastructure.interfaces;

import com.sera.chatting.domain.ChattingRoom;

public interface ChattingRoomReader {
	ChattingRoom readByChattingRoomId(String roomId);
}
