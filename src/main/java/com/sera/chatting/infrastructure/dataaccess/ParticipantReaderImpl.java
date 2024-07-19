package com.sera.chatting.infrastructure.dataaccess;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.common.domain.valueobject.UserId;
import com.sera.chatting.domain.Participant;
import com.sera.chatting.infrastructure.interfaces.ParticipantReader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ParticipantReaderImpl implements ParticipantReader {
	private final ParticipantRepository participantRepository;

	@Override
	public Set<UserId> readByChattingRoomId(RoomId roomId) {
		return participantRepository.findAllByChattingRoomId(roomId)
			.stream()
			.map(Participant::getUserId)
			.collect(Collectors.toSet());
	}
}
