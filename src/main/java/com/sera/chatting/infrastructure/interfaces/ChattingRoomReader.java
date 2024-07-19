package com.sera.chatting.infrastructure.interfaces;

import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.domain.ChattingRoom;

public interface ChattingRoomReader {
	ChattingRoom readByChattingRoomId(RoomId roomId);
}
