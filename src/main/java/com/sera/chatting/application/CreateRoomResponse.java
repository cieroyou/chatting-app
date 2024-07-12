package com.sera.chatting.application;

import com.sera.chatting.application.dto.common.AckData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateRoomResponse implements AckData {
	String roomId;
}
