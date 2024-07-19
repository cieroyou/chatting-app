package com.sera.chatting.infrastructure.dataaccess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.sera.chatting.common.domain.util.ChattingRoomFixture;
import com.sera.chatting.domain.ChattingRoom;

import jakarta.persistence.EntityNotFoundException;

@DataJpaTest
@Import(ChattingRoomReaderImpl.class)
class ChattingRoomReaderImplTest {

	@Autowired
	private ChattingRoomRepository repository;

	@Autowired
	private ChattingRoomReaderImpl reader;

	private final ChattingRoom EXIST_ROOM = ChattingRoomFixture.NORMAL.getChattingRoom();
	private final ChattingRoom NON_EXIST_ROOM = ChattingRoom.builder()
		.name("채팅방")
		.maxParticipants(3)
		.description("채팅방입니다")
		.build();

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		repository.save(EXIST_ROOM);

	}

	@Test
	void whenReadChattingRoomByRoomId_ThenReturnChattingRoom() {
		// given
		// when
		ChattingRoom found = reader.readByChattingRoomId(EXIST_ROOM.getChattingRoomId());
		// then
		assertAll(
			() -> assertEquals(EXIST_ROOM.getId(), found.getId()),
			() -> assertEquals(EXIST_ROOM.getChattingRoomId(), found.getChattingRoomId()),
			() -> assertEquals(EXIST_ROOM.getName(), found.getName()),
			() -> assertEquals(EXIST_ROOM.getMaxParticipants(), found.getMaxParticipants()),
			() -> assertEquals(EXIST_ROOM.getDescription(), found.getDescription())
		);
	}

	@Test
	void whenReadByNonExistingChattingRoomId_ThenThrowEntityNotFoundException() {
		// String nonExistingId = "non-existing-id";
		String expectedErrorMessage = "ChattingRoom not found, chattingRoomId: %s".formatted(
			NON_EXIST_ROOM.getChattingRoomId());
		EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
			() -> reader.readByChattingRoomId(NON_EXIST_ROOM.getChattingRoomId()));
		assertEquals(expectedErrorMessage, thrown.getMessage());
	}
}