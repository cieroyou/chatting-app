package com.sera.chatting.common.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

@Getter
@Setter
@Slf4j
@ToString
@Builder
public class SocketMessage<T> {
    @NotNull
    private Type type;
    @NotNull
    private String messageType;
    private T body;
    private String transactionId;
    private String sessionId;
    private ZonedDateTime timestamp;

}
