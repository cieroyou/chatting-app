package com.sera.chatting.application.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestBody implements MessageBody {
	private String command;
}
