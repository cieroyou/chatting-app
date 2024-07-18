package com.sera.chatting.application.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SessionEventPublisher {
	private final List<SessionEventListener> listeners = new ArrayList<>();

	public void addListener(SessionEventListener listener) {
		listeners.add(listener);
	}

	public void removeListener(SessionEventListener listener) {
		listeners.remove(listener);
	}

	public void publishSessionClosed(String sessionId) {
		for (SessionEventListener listener : listeners) {
			listener.onSessionClosed(sessionId);
		}
	}
}