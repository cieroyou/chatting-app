package com.sera.chatting.infrastructure.dataaccess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.sera.chatting.common.domain.util.ChattingRoomFixture;
import com.sera.chatting.domain.ChattingRoom;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ChattingRoomRepositoryTest {
	@Autowired
	private ChattingRoomRepository repository;

	@DisplayName("채팅방을 저장하면 id로 저장된 채팅방을 찾을 수 있다")
	@Test
	void whenSave_thenFindById() {
		// given
		ChattingRoom chattingRoom = ChattingRoomFixture.NORMAL.getChattingRoom();
		repository.save(chattingRoom);
		// when
		Optional<ChattingRoom> found = repository.findById(chattingRoom.getId());
		//then
		found.ifPresent(room -> {
			assertAll(
				() -> assertEquals(chattingRoom.getId(), room.getId()),
				() -> assertEquals(chattingRoom.getChattingRoomId(), room.getChattingRoomId()),
				() -> assertEquals(chattingRoom.getName(), room.getName()),
				() -> assertEquals(chattingRoom.getMaxParticipants(), room.getMaxParticipants()),
				() -> assertEquals(chattingRoom.getDescription(), room.getDescription())
			);
		});
	}

	@DisplayName("채팅방을 저장하면 chattingRoomId로 저장된 채팅방을 찾을 수 있다")
	@Test
	void whenSave_thenFindByChattingRoomId() {
		// given
		ChattingRoom chattingRoom = ChattingRoomFixture.NORMAL.getChattingRoom();
		repository.save(chattingRoom);
		// when
		Optional<ChattingRoom> found = repository.findByChattingRoomId(chattingRoom.getChattingRoomId());
		//then
		found.ifPresent(room -> {
			assertAll(
				() -> assertEquals(chattingRoom.getId(), room.getId()),
				() -> assertEquals(chattingRoom.getChattingRoomId(), room.getChattingRoomId()),
				() -> assertEquals(chattingRoom.getName(), room.getName()),
				() -> assertEquals(chattingRoom.getMaxParticipants(), room.getMaxParticipants()),
				() -> assertEquals(chattingRoom.getDescription(), room.getDescription())
			);
		});
	}
}
