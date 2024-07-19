package com.sera.chatting.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sera.chatting.common.domain.util.ChattingRoomFixture;
import com.sera.chatting.common.domain.valueobject.UserId;
import com.sera.chatting.common.exception.BaseException;
import com.sera.chatting.common.exception.ErrorCode;

@ExtendWith(MockitoExtension.class)
class ChattingRoomServiceTest {

	@InjectMocks
	private ChattingRoomService chattingRoomService;

	private final UserId USER1 = new UserId(UUID.randomUUID());
	private final UserId USER2 = new UserId(UUID.randomUUID());
	private final UserId USER3 = new UserId(UUID.randomUUID());
	private final UserId USER4 = new UserId(UUID.randomUUID());

	@DisplayName("유저가 참여할 때_이미 방에 존재하여_참여에 실패한다")
	@Test
	void whenJoinRoom_thenFailBecauseAlreadyJoined() {
		// given
		ChattingRoom chattingRoom = ChattingRoomFixture.NORMAL.getChattingRoom();
		Set<UserId> alreadyJoined = Set.of(USER1, USER2);
		// when-then
		var thrown = assertThrows(BaseException.class,
			() -> chattingRoomService.addParticipant(chattingRoom, alreadyJoined, USER1));
		assertEquals(ErrorCode.ALREADY_A_PARTICIPANT, thrown.getErrorCode());
	}

	@DisplayName("유저가 참여할 때_방이 가득 차서_참여에 실패한다")
	@Test
	void whenJoinRoom_thenFailBecauseRoomIsFull() {
		// given
		ChattingRoom chattingRoom = ChattingRoomFixture.NORMAL.getChattingRoom();
		Set<UserId> alreadyJoined = Set.of(USER1, USER2, USER3);
		// String userId = "user4";
		// when-then
		var thrown = assertThrows(BaseException.class,
			() -> chattingRoomService.addParticipant(chattingRoom, alreadyJoined, USER4));
		assertEquals(ErrorCode.ROOM_FULL, thrown.getErrorCode());
	}

	@DisplayName("유저가 참여할 때_참여에 성공한다")
	@Test
	void whenJoinRoom_thenSuccess() {
		// given
		ChattingRoom chattingRoom = ChattingRoomFixture.NORMAL.getChattingRoom();
		Set<UserId> alreadyJoined = Set.of(USER1, USER2);
		// when
		chattingRoomService.addParticipant(chattingRoom, alreadyJoined, USER3);
		// then
		assertTrue(true);
	}
}