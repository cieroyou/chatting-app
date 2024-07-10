package com.sera.chatting.infrastructure.dataaccess;

import com.sera.chatting.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
}
