package com.sera.chatting.websocket.application;

import com.sera.chatting.websocket.common.Body;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JoinChattingRequest extends Body {
    String roomId;
    Long userId;
}
