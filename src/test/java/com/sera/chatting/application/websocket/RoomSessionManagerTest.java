package com.sera.chatting.application.websocket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoomSessionManagerTest {

	@InjectMocks
	private RoomSessionManager roomSessionManager;

	@Mock
	private SessionManager sessionManager;

	private final String TEST_ROOM_ID = "testRoom";
	private final String ALREADY_PARTICIPANT = "testSession";
	private final String TOBE_JOINED_PARTICIPANT = "tobeJoinedSession";

	@BeforeEach
	void setUp() {
		roomSessionManager.addSessionToRoom(TEST_ROOM_ID, ALREADY_PARTICIPANT);
	}

	@DisplayName("방에 세션을 추가하면 성공적으로 추가된다")
	@Test
	void addSessionToRoom() {
		// given
		roomSessionManager.addSessionToRoom(TEST_ROOM_ID, TOBE_JOINED_PARTICIPANT);
		// when
		Set<String> sessionsInRoom = roomSessionManager.getSessionsInRoom(TEST_ROOM_ID);
		// then
		assertTrue(sessionsInRoom.contains(TOBE_JOINED_PARTICIPANT));
	}

	@DisplayName("이미 방에 있는 세션을 제거하면_해당세션이_제거되고_방이_제거된다")
	@Test
	void removeSessionFromRoom() {
		// when
		roomSessionManager.removeSessionFromRoom(TEST_ROOM_ID, ALREADY_PARTICIPANT);
		// then
		Set<String> sessionsInRoom = roomSessionManager.getSessionsInRoom(TEST_ROOM_ID);
		assertFalse(sessionsInRoom.contains(ALREADY_PARTICIPANT));
		assertTrue(roomSessionManager.getSessionsInRoom(TEST_ROOM_ID).isEmpty());
	}

	@DisplayName("존재하지 않는 방에 메세지를 보내면 아무것도 일어나지 않는다")
	@Test
	void sendMessageToNonExistentRoom() {
		roomSessionManager.sendMessageToRoom("nonExistentRoom", "Hello, Room!");
		verify(sessionManager, never()).sendMessage(anyString(), anyString());
	}

	@DisplayName("방에 메세지를 보내면 방에 있는 모든 세션에게 메세지가 전달된다")
	@Test
	void sendMessageToRoom_ThenSendMessageEveryOne() {
		// given
		int expectedMessageCount = roomSessionManager.getSessionsInRoom(TEST_ROOM_ID).size();
		String expectedMessage = "Hello, Room!";
		// when
		roomSessionManager.sendMessageToRoom(TEST_ROOM_ID, expectedMessage);
		// then
		verify(sessionManager, times(expectedMessageCount)).sendMessage(ALREADY_PARTICIPANT, expectedMessage);
	}

	@DisplayName("방에 없는 세션에게 메세지를 보내면 아무것도 일어나지 않는다")
	@Test
	void sendMessageToRoom() {
		String expectedMessage = "Hello, Room!";
		// when
		roomSessionManager.sendMessageToRoom(TEST_ROOM_ID, expectedMessage);
		// then
		verify(sessionManager, never()).sendMessage("Nobody", expectedMessage);
	}

	@DisplayName("세션종료 이벤트가 발생하면 해당 세션을 모든 방에서 제거한다")
	@Test
	void onSessionClosed() {
		// when
		roomSessionManager.onSessionClosed(ALREADY_PARTICIPANT);
		// then
		assertFalse(roomSessionManager.getSessionsInRoom(TEST_ROOM_ID).contains(ALREADY_PARTICIPANT));
	}
}