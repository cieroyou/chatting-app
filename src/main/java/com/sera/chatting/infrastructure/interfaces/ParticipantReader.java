package com.sera.chatting.infrastructure.interfaces;

import java.util.Set;

import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.common.domain.valueobject.UserId;

public interface ParticipantReader {
	Set<UserId> readByChattingRoomId(RoomId roomId);
}
