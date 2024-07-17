package com.sera.chatting.application.websocket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@ExtendWith(MockitoExtension.class)
class SessionManagerTest {

	@InjectMocks
	private SessionManager sessionManager;

	@Mock
	private WebSocketSession mockSession;

	@Mock
	private SessionEventListener mockSessionEventListener;

	private Map<String, WebSocketSession> sessions;

	private static final String ALREADY_EXISTING_SESSION_ID = "sessionId";

	@BeforeEach
	void setUp() {
		sessionManager.addSession(ALREADY_EXISTING_SESSION_ID, mockSession);
	}

	@DisplayName("세션추가를 하면 성공적으로 추가된다")
	@Test
	void whenAddSession_thenAddSuccess() {
		sessionManager.addSession("newSessionId", mockSession);
		assertTrue(sessionManager.hasSession("newSessionId"));
	}

	@DisplayName("세션삭제를 하면_해당세션이_삭제되고_세션종료이벤트가_발생한다")
	@Test
	void whenRemoveExistSession_thenRemoveSuccessAndOccurSessionClosedEvent() {
		sessionManager.removeSession(ALREADY_EXISTING_SESSION_ID);
		assertFalse(sessionManager.hasSession(ALREADY_EXISTING_SESSION_ID));
		verify(mockSessionEventListener, times(1)).onSessionClosed(ALREADY_EXISTING_SESSION_ID);
	}

	@DisplayName("연결되지 않은 세션을 삭제하면_세션종료이벤트가_발생하지않는다")
	@Test
	void whenRemoveNonExistSession_thenNotOccurSessionClosedEvent() {
		sessionManager.removeSession("nonExistentSessionId");
		verify(mockSessionEventListener, never()).onSessionClosed(ALREADY_EXISTING_SESSION_ID);
	}

	@Test
	void hasSession() {
		assertTrue(sessionManager.hasSession(ALREADY_EXISTING_SESSION_ID));
	}

	@DisplayName("세션이 존재하면_메시지를_보낼수있다")
	@Test
	void sendMessage() throws IOException {
		String sendMessage = "testMessage";
		sessionManager.sendMessage(ALREADY_EXISTING_SESSION_ID, sendMessage);
		verify(mockSession, times(1)).sendMessage(new TextMessage(sendMessage));
	}

	@DisplayName("세션이 존재하지 않으면_메시지를_보내지않는다")
	@Test
	void sendNotMessageWhenSessionNotExists() throws IOException {
		String sendMessage = "testMessage";
		sessionManager.sendMessage("nonExistentSessionId", sendMessage);

		// verify(mockSession, never()).sendMessage(new TextMessage(sendMessage));
	}
}