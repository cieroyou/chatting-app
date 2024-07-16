package com.sera.chatting.domain;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.sera.chatting.common.exception.BaseException;
import com.sera.chatting.common.exception.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChattingRoomService {
	public void addParticipant(ChattingRoom room, Set<String> participants, String userId) {
		// 참여자가 방에 이미 있는지 확인
		if (participants.contains(userId)) {
			throw new BaseException("이미 참여한 방입니다. userId: %s, roomId: %s".formatted(userId, room.getChattingRoomId())
				, ErrorCode.ALREADY_A_PARTICIPANT);
		}
		// 방 최대 인원수 확인
		if (room.getMaxParticipants() <= participants.size()) {
			throw new BaseException("방이 가득 찼습니다. userId: %s, roomId: %s".formatted(userId, room.getChattingRoomId())
				, ErrorCode.ROOM_FULL);

		}
	}
}
