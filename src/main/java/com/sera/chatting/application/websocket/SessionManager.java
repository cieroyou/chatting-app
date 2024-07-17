package com.sera.chatting.application.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class SessionManager {
	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final SessionEventListener sessionEventListener;

	public void addSession(String sessionId, WebSocketSession session) {
		if (!StringUtils.hasText(sessionId)) {
			log.error("Failed to add session. sessionId is empty.");
			return;
		}
		if (session == null) {
			log.error("Failed to add session. session is null. sessionId: {}", sessionId);
			return;
		}
		if (sessions.containsKey(sessionId)) {
			log.warn("Failed to add session. session already exists. sessionId: {}", sessionId);
			return;
		}
		sessions.put(sessionId, session);
		log.info("Session added. sessionId: {}", sessionId);
	}

	public void removeSession(String sessionId) {
		if (!StringUtils.hasText(sessionId)) {
			log.error("Failed to add session. sessionId is empty.");
			return;
		}
		WebSocketSession session = sessions.remove(sessionId);
		if (session != null) {
			notifySessionClosed(sessionId);
			log.info("Session removed: {}", sessionId);
		} else {
			log.warn("Attempted to remove non-existing session: {}", sessionId);
		}
	}

	public WebSocketSession getSession(String sessionId) {
		return sessions.get(sessionId);
	}

	public boolean hasSession(String sessionId) {
		return sessions.containsKey(sessionId);
	}

	public void sendMessage(String sessionId, String message) {
		WebSocketSession session = sessions.get(sessionId);
		if (session != null) {
			try {
				session.sendMessage(new TextMessage(message));
				log.info("Message sent to session: {}", sessionId);

			} catch (IOException e) {
				log.error("Failed to send message to session: {}", sessionId, e);
			}
		}
	}

	private void notifySessionClosed(String sessionId) {
		sessionEventListener.onSessionClosed(sessionId);
		log.info("Session closed event notified for session: {}", sessionId);
	}
}
