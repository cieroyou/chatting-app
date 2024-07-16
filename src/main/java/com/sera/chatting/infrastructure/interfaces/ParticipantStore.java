package com.sera.chatting.infrastructure.interfaces;

import com.sera.chatting.domain.Participant;

public interface ParticipantStore {
	void save(Participant participant);
}
