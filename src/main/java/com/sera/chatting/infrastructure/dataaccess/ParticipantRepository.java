package com.sera.chatting.infrastructure.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.domain.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	List<Participant> findAllByChattingRoomId(RoomId chattingRoomId);
}
