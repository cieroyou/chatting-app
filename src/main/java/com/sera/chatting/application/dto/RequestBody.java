package com.sera.chatting.application.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RequestBody implements MessageBody {
	private String command;
}
