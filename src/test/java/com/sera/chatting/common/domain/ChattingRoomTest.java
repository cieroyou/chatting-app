package com.sera.chatting.common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import com.sera.chatting.common.domain.util.ChattingRoomFixture;
import com.sera.chatting.domain.ChattingRoom;

class ChattingRoomTest {

	@Test
	@DisplayName("채팅방 생성 테스트")
	public void createChattingRoomTest() {
		// given-when
		ChattingRoom chattingRoom = ChattingRoomFixture.NORMAL.getChattingRoom();
		// then
		assertEquals(chattingRoom.getName(), ChattingRoomFixture.NORMAL.getName());
		assertEquals(chattingRoom.getDescription(), ChattingRoomFixture.NORMAL.getDescription());
		assertTrue(StringUtils.hasText(chattingRoom.getChattingRoomId()));
	}

	@DisplayName("채팅방 생성 테스트 - 채팅방 이름 필수값")
	@Test
	public void shouldChattingRoomHasName() {
		assertThrows(IllegalArgumentException.class, () -> {
			ChattingRoom.builder()
				.description("채팅방입니다")
				.build();
		});
	}

	@DisplayName("채팅방 생성 테스트 - 채팅방 이름 Not Blank")
	@Test
	public void createRoomTest() {
		// given
		String name = " ";
		String description = "채팅방입니다";
		int maxParticipants = 10;
		// when-then
		assertThrows(IllegalArgumentException.class, () -> {
			ChattingRoom.createRoom(name, maxParticipants, description);
		});
	}
}