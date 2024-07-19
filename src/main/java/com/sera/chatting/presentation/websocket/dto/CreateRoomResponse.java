package com.sera.chatting.presentation.websocket.dto;

import java.util.UUID;

import com.sera.chatting.presentation.websocket.dto.common.AckData;

public record CreateRoomResponse(UUID roomId) implements AckData {
}
