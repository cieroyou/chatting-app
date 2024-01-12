package com.sera.chatting.common.message;

import lombok.Getter;

@Getter
public enum Type {
    REQUEST("request"), RESPONSE("response"), EVENT("event");
    private final String value;
    Type(String value) {
        this.value = value;
    }
}
