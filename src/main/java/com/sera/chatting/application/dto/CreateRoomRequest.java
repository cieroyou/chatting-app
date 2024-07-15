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
	Integer maxParticipants;
	String description;
	@Serial
	private static final long serialVersionUID = 2L; // 직렬화 버전 ID

	@Builder(builderMethodName = "createRoomRequestBuilder")
	public CreateRoomRequest(String command, String roomName, Integer maxParticipants, String description) {
		super(command);
		this.roomName = roomName;
		this.maxParticipants = maxParticipants;
		this.description = description;
	}

	@Override
	public String toString() {
		return "CreateRoomRequest{" +
			"roomName='" + roomName + '\'' +
			", maxParticipants=" + maxParticipants +
			", description='" + description + '\'' +
			"} " + super.toString();
	}
}
