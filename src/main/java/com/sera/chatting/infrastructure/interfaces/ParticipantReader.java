package com.sera.chatting.infrastructure.interfaces;

import java.util.Set;

public interface ParticipantReader {
	Set<String> readByChattingRoomId(String roomId);
}
