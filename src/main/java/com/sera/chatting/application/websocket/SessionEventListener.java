package com.sera.chatting.application.websocket;

public interface SessionEventListener {
	void onSessionClosed(String sessionId);
}
