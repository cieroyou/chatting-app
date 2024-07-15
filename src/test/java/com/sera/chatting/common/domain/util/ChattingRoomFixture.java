package com.sera.chatting.common.domain.util;

import com.sera.chatting.domain.ChattingRoom;

import lombok.Getter;

@Getter
public enum ChattingRoomFixture {
	NORMAL("채팅방", 10, "채팅방입니다");

	private final String name;
	private final Integer maxParticipants;
	private final String description;

	ChattingRoomFixture(String name, Integer maxParticipants, String description) {
		this.name = name;
		this.maxParticipants = maxParticipants;
		this.description = description;
	}

	public ChattingRoom getChattingRoom() {
		return ChattingRoom.builder()
			.name(name)
			.maxParticipants(maxParticipants)
			.description(description)
			.build();
	}
}
