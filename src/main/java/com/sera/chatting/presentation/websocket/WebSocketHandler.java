package com.sera.chatting.presentation.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.chatting.application.RequestMessageConverter;
import com.sera.chatting.application.dto.RequestMessage;
import com.sera.chatting.common.message.CommonEventMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 다수의 클라이언트의 메세지를 처리하는 핸들러
 * 채팅에서는 텍스트 기반의 메세지가 오고 가므로 TextWebSocketHandler 를 상속받는다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
	private final ObjectMapper objectMapper;
	private final RequestMessageConverter webSocketMessageConverter;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("afterConnectionEstablished: {}", session);
		CommonEventMessage commonEventMessage = CommonEventMessage.toConnectionEstablishedMessage(session.getId());
		session.sendMessage(new TextMessage(objectMapper.writeValueAsString(commonEventMessage)));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("afterConnectionClosed: {}", session);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
		String payload = textMessage.getPayload();
		log.info("handleTextMessage: {}", payload);
		RequestMessage requestMessage = webSocketMessageConverter.convertToRequestMessage(session, payload);
		session.sendMessage(new TextMessage("Hello Client!"));
	}
}
