package com.sera.chatting.infrastructure.dataaccess;

import org.springframework.stereotype.Component;

import com.sera.chatting.domain.Participant;
import com.sera.chatting.infrastructure.interfaces.ParticipantStore;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ParticipantStoreImpl implements ParticipantStore {
	private final ParticipantRepository participantRepository;

	@Override
	public void save(Participant participant) {
		participantRepository.save(participant);
	}
}
