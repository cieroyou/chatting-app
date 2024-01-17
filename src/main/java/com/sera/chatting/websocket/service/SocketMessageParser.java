package com.sera.chatting.websocket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.chatting.common.message.CommonRequestMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketMessageParser {

    private final ObjectMapper objectMapper;

    public CommonRequestMessage getSocketMessage(WebSocketSession session, String payload) throws JsonProcessingException {
        log.debug("event: receivedMessage, sessionId: {}, rawMessage: {}", session.getId(), payload);
        CommonRequestMessage rawMessage = null;
        try {
            rawMessage = objectMapper.readValue(payload, CommonRequestMessage.class);
            return rawMessage;
        } catch (JsonProcessingException e) {

        } catch (Exception e) {

        }
        return rawMessage;
//        CommonRequestMessage rawMessage = null;
//        try {
//            rawMessage = CommonRequestMessage.of(payload);
//            log.debug("event: receivedMessage, sessionId: {}, result: {}", session.getId(), rawMessage);
//            return rawMessage;
//        } catch (Exception e) {
//            log.error("requestMessage: {}, sessionId: {}, errorCode: {}, cause: {}, errorMsg: Message cannot be parsed. {}, e: {}",
//                    payload, session.getId(), e.getMessage(), e);
//            throw e;
//        }
    }
}
