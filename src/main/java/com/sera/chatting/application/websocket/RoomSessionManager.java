package com.sera.chatting.application.websocket;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * 방에 있는 세션을 관리하는 클래스
 * roomSessions 는 외부에서 직접 접근하지 않도록 한다.
 */
@RequiredArgsConstructor
@Service
public class RoomSessionManager implements SessionEventListener {
	private final Map<String, Set<String>> roomSessions = new ConcurrentHashMap<>();
	private final SessionManager sessionManager;

	public void addSessionToRoom(String roomId, String sessionId) {
		roomSessions.computeIfAbsent(roomId, key -> ConcurrentHashMap.newKeySet()).add(sessionId);
	}

	/**
	 * 방에 있는 세션을 제거한다. 방에 세션이 없으면 방을 제거한다.
	 */
	public void removeSessionFromRoom(String roomId, String sessionId) {
		Set<String> sessionsInRoom = roomSessions.get(roomId);
		if (sessionsInRoom != null) {
			sessionsInRoom.remove(sessionId);
			if (sessionsInRoom.isEmpty()) {
				roomSessions.remove(roomId);
			}
		}
	}

	public Set<String> getSessionsInRoom(String roomId) {
		return roomSessions.getOrDefault(roomId, Collections.emptySet());
	}

	public void sendMessageToRoom(String roomId, String message) {
		Set<String> sessionsInRoom = this.getSessionsInRoom(roomId);
		// if (sessionsInRoom != null) {
		sessionsInRoom.forEach(sessionId -> {
			sessionManager.sendMessage(sessionId, message);
		});
		// }
	}

	@Override
	public void onSessionClosed(String sessionId) {
		// 모든 방에서 sessionId를 제거
		roomSessions.forEach((roomId, sessions) -> {
			if (sessions.contains(sessionId)) {
				removeSessionFromRoom(roomId, sessionId);
			}
		});
	}
}
