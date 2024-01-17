package com.sera.chatting.websocket.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.sera.chatting.common.message.CommonRequestMessage;
import com.sera.chatting.websocket.common.Body;
import com.sera.chatting.websocket.common.SocketMessageParsingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//@JsonComponent 를 사용하지 않고, @Component 를 사용하면 해당 CommonRequestMessage 에 @JsonDeserialize(using = SocketMessageDeserializer) 를 붙여줘야 한다.
@Component
@RequiredArgsConstructor
@Slf4j
public class SocketMessageDeserializer extends JsonDeserializer<CommonRequestMessage<?>> {
    private static final long serialVersionUID = 1883547683050039861L;
    private final BodyDeserializer bodyDeserializer;

    //    // 방입장
//    {
//        "transaction_id": "a#DJlkf",
//            "session_id": "ajklnsdf",
//            "type": "request",
//            "message_type": "join"
//        "body": {
//        "room_id": "jsjdf ",
//    }
//    }
    // transactionId 필수값
    // type 필수값, must request
    // messageType 필수값
    // body 필수값
    @Override
    public CommonRequestMessage<?> deserialize(JsonParser p, DeserializationContext ctxt) {
        try {
            JsonNode node = p.getCodec().readTree(p);
            String transactionId = parseAndCheckBlank("transactionId", node.get("transaction_id").asText());
            String sessionId = parseAndCheckBlank("sessionId", node.get("session_id").asText());
            String messageType = parseAndCheckBlank("messageType", node.get("message_type").asText());
            Body<?> body = bodyDeserializer.deserialize(messageType, node.get("body"));
            return CommonRequestMessage.builder()
                    .transactionId(transactionId)
                    .sessionId(sessionId)
                    .messageType(messageType)
                    .body(body)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 문자열이 isBlank 인 경우 throw Exception, 아닌 경우 String 으로 반환
     */
    private String parseAndCheckBlank(String fieldName, String value) {
        if (!StringUtils.hasText(value)) throw new SocketMessageParsingException(fieldName, "is empty");
        return value;
    }
}