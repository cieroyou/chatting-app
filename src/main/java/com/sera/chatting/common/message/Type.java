package com.sera.chatting.common.message;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Type {
    REQUEST("request"), RESPONSE("response"), EVENT("event");
    private final String value;

    Type(String value) {
        this.value = value;
    }

    private static final Map<String, Type> BY_JSON_STRING = new HashMap<>();

    static {
        for (Type e : values()) {
            BY_JSON_STRING.put(e.value, e);
        }
    }

    public static Type valueOfJsonString(String jsonStr) {
        return BY_JSON_STRING.get(jsonStr);
    }
}
