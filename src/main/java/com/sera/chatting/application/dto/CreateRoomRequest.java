package com.sera.chatting.application.dto;

import static lombok.AccessLevel.*;

import java.io.Serial;
import java.io.Serializable;

import com.sera.chatting.application.dto.common.RequestBody;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class CreateRoomRequest extends RequestBody implements Serializable {
	String roomName;

	@Serial
	private static final long serialVersionUID = 2L; // 직렬화 버전 ID

	@Builder(builderMethodName = "createRoomRequestBuilder")
	public CreateRoomRequest(String command, String roomName) {
		super(command);
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		return "CreateRoomRequest{" +
			"name='" + roomName + '\'' +
			"} " + super.toString();
	}
}
