package com.sera.chatting.common.message;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sera.chatting.websocket.service.SocketMessageDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = SocketMessageDeserializer.class)
public class CommonRequestMessage<T> {
    private Type type;
    private String messageType;
    private T body;
    private String transactionId;
    private String sessionId;
    private ZonedDateTime timestamp;

    @Builder
    private CommonRequestMessage(String messageType, T body, String transactionId, String sessionId, ZonedDateTime timestamp) {
        this.type = Type.REQUEST;
        this.messageType = messageType;
        this.body = body;
        this.transactionId = transactionId;
        this.sessionId = sessionId;
        this.timestamp = ZonedDateTime.now();
    }
}
