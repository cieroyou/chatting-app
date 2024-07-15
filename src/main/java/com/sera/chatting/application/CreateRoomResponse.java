package com.sera.chatting.application;

import static lombok.AccessLevel.*;

import com.sera.chatting.application.dto.common.AckData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Getter
public class CreateRoomResponse implements AckData {
	String roomId;
}
