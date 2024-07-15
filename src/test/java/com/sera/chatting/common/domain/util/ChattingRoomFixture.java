package com.sera.chatting.common.domain.util;

import com.sera.chatting.domain.ChattingRoom;

import lombok.Getter;

@Getter
public enum ChattingRoomFixture {
	NORMAL("채팅방", "채팅방입니다");

	private final String name;
	private final String description;

	ChattingRoomFixture(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public ChattingRoom getChattingRoom() {
		return ChattingRoom.builder()
			.name(name)
			.description(description)
			.build();
	}
}
