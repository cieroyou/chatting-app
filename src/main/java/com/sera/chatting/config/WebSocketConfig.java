package com.sera.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.sera.chatting.presentation.websocket.WebSocketHandler;

import lombok.RequiredArgsConstructor;

/**
 * WebSocket을 활성화하기 위한 Config
 *
 * @EnableWebSocket 어노테이션을 사용하여 WebSocket 활성화
 * ws://localhost:8080/ws Endpoint 로 커넥션 연결 가능
 */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
	private final WebSocketHandler webSocketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/ws").setAllowedOrigins("*");
	}

}
