package com.sera.chatting.infrastructure.interfaces;

import com.sera.chatting.domain.ChattingRoom;

public interface ChattingRoomStore {
	ChattingRoom save(ChattingRoom room);
}
