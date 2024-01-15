package com.sera.chatting.common.dataaccess;

import com.sera.chatting.common.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
}
