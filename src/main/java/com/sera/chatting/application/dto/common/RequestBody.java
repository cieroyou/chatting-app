package com.sera.chatting.application.dto.common;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sera.chatting.application.dto.CreateRoomRequest;
import com.sera.chatting.application.dto.JoinRoomRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
	property = "command"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = CreateRoomRequest.class, name = "create_room"),
	@JsonSubTypes.Type(value = JoinRoomRequest.class, name = "join_room")
})
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestBody implements MessageBody, Serializable {
	@Serial
	private static final long serialVersionUID = 1L; // 직렬화 버전 ID
	private String command;

	@Override
	public String toString() {
		return "RequestBody{" +
			"command='" + command + '\'' +
			'}';
	}
}
