package com.sera.chatting.presentation.websocket.dto;

import static lombok.AccessLevel.*;

import com.sera.chatting.presentation.websocket.dto.common.AckData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Getter
public class CreateRoomResponse implements AckData {
	String roomId;
}
