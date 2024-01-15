package com.sera.chatting.api.usecase;

import com.sera.chatting.common.dataaccess.ChattingRoomRepository;
import com.sera.chatting.common.domain.ChattingRoom;
import com.sera.chatting.api.usecase.dto.RoomCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingRoomUsecase {

    private final ChattingRoomRepository chattingRoomRepository;

    /**
     * 채팅방 생성 후 채팅방 아이디 반환
     *
     * @return 채팅방 아이디
     */
    public String createRoom(RoomCommand.CreateRoom createRoom) {
        var room = ChattingRoom.createRoom(createRoom.name(), createRoom.description());
        chattingRoomRepository.save(room);
        return room.getChattingRoomId();
    }
}
