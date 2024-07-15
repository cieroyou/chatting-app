package com.sera.chatting.presentation.websocket.dto;

import com.sera.chatting.presentation.websocket.dto.common.AckData;

public record CreateRoomResponse(String roomId) implements AckData {
}
