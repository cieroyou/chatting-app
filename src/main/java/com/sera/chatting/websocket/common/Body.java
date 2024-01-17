package com.sera.chatting.websocket.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sera.chatting.websocket.service.WrapperDeserializer;

@JsonDeserialize(using = WrapperDeserializer.class)
public class Body<T> {
    T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
