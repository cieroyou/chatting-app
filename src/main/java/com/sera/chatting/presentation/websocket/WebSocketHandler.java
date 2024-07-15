package com.sera.chatting.presentation.websocket;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.chatting.application.ChattingRoomFacade;
import com.sera.chatting.presentation.websocket.dto.common.AckBody;
import com.sera.chatting.presentation.websocket.dto.common.AckData;
import com.sera.chatting.presentation.websocket.dto.common.AckMessage;
import com.sera.chatting.presentation.websocket.dto.common.RequestBody;
import com.sera.chatting.presentation.websocket.dto.common.RequestMessage;
import com.sera.chatting.common.CommandHandler;
import com.sera.chatting.common.converter.AckMessageJsonConverter;
import com.sera.chatting.common.converter.RequestMessageConverter;
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
	private final AckMessageJsonConverter ackMessageJsonConverter;
	private final ChattingRoomFacade roomFacade;
	private final List<CommandHandler> commandHandlers;

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
		// 1. 클라이언트로부터 받은 메세지를 RequestMessage 로 변환
		RequestMessage requestMessage = webSocketMessageConverter.convertToRequestMessage(session, payload);

		// 2. 요청에 따른 비즈니스로직 함수 수행하여 리턴값 가져오기
		AckBody<AckData> body = null;
		RequestBody request = requestMessage.getBody();
		String command = request.getCommand();
		for (@SuppressWarnings("rawtypes") CommandHandler commandHandler : commandHandlers) {
			if (commandHandler.supports(command)) {
				//noinspection unchecked
				body = commandHandler.handleCommand(session.getId(), request);
			}
		}

		// // 3. 리턴(Response)를 Json으로 변환하여 클라이언트에게 응답
		AckMessage ackMessage = new AckMessage(requestMessage.getTransactionId(), body);
		//
		String ackMessageJson = ackMessageJsonConverter.convertToJson(ackMessage);
		session.sendMessage(new TextMessage(ackMessageJson));
	}
}
