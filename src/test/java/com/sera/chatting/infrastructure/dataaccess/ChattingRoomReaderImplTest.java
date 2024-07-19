package com.sera.chatting.infrastructure.dataaccess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.sera.chatting.common.domain.util.ChattingRoomFixture;
import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.domain.ChattingRoom;

import jakarta.persistence.EntityNotFoundException;

@DataJpaTest
@Import(ChattingRoomReaderImpl.class)
class ChattingRoomReaderImplTest {

	@Autowired
	private ChattingRoomRepository repository;

	@Autowired
	private ChattingRoomReaderImpl reader;

	@Test
	void whenReadChattingRoomByRoomId_ThenReturnChattingRoom() {
		// given
		ChattingRoom chattingRoom = ChattingRoomFixture.NORMAL.getChattingRoom();
		repository.save(chattingRoom);
		// when
		ChattingRoom found = reader.readByChattingRoomId(chattingRoom.getChattingRoomId());
		// then
		assertAll(
			() -> assertEquals(chattingRoom.getId(), found.getId()),
			() -> assertEquals(chattingRoom.getChattingRoomId(), found.getChattingRoomId()),
			() -> assertEquals(chattingRoom.getName(), found.getName()),
			() -> assertEquals(chattingRoom.getMaxParticipants(), found.getMaxParticipants()),
			() -> assertEquals(chattingRoom.getDescription(), found.getDescription())
		);
	}

	@Test
	void whenReadByNonExistingChattingRoomId_ThenThrowEntityNotFoundException() {
		String nonExistingId = "non-existing-id";
		String expectedErrorMessage = "ChattingRoom not found, chattingRoomId: %s".formatted(nonExistingId);
		EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
			() -> reader.readByChattingRoomId(new RoomId(UUID.fromString("non-existing-id"))));
		assertEquals(expectedErrorMessage, thrown.getMessage());
	}
}