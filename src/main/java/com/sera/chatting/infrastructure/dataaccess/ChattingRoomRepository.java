package com.sera.chatting.infrastructure.dataaccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.domain.ChattingRoom;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
	Optional<ChattingRoom> findByChattingRoomId(RoomId chattingRoomId);
}
