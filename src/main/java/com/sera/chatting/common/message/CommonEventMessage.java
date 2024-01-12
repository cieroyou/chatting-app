package com.sera.chatting.common.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonEventMessage {
    private Type type;
    private String messageType;
    private String body;
    private String transactionId;
    private String sessionId;
    private ZonedDateTime timestamp;

    @Builder
    private CommonEventMessage(String messageType, String body, String transactionId, String sessionId, ZonedDateTime timestamp) {
        this.type = Type.EVENT;
        this.messageType = messageType;
        this.body = body;
        this.transactionId = transactionId;
        this.sessionId = sessionId;
        this.timestamp = ZonedDateTime.now();
    }

    public static CommonEventMessage toConnectionEstablishedMessage(String sessionId) {
        return CommonEventMessage.builder()
                .messageType("connected")
                .sessionId(sessionId)
                .build();
    }
}
