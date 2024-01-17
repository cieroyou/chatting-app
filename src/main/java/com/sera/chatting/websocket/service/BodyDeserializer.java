package com.sera.chatting.websocket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sera.chatting.websocket.application.JoinChattingRequest;
import com.sera.chatting.websocket.common.Body;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BodyDeserializer {
    private final ObjectMapper objectMapper;

    public Body deserialize(String messageType, JsonNode bodyNode) throws JsonProcessingException {
        return objectMapper.treeToValue(bodyNode, JoinChattingRequest.class);
    }
}
