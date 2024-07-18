package com.sera.chatting.presentation.websocket.dto.common;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventBody implements MessageBody, Serializable {
	@Serial
	private static final long serialVersionUID = 4L; // 직렬화 버전 ID
	private String event;

	@Override
	public String toString() {
		return "RequestBody{" +
			"command='" + event + '\'' +
			'}';
	}
}
