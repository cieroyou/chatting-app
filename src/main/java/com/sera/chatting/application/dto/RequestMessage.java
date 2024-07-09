package com.sera.chatting.application.dto;

import java.time.Instant;

import lombok.Getter;

/**
 * RequestMessage는 클라이언트로부터 받은 메시지를 표현하는 DTO(Data Transfer Object)입니다.
 * 이 클래스는 도메인 로직과 직접적인 관련이 없고, 애플리케이션 계층에서 데이터 전송을 위해 사용됩니다.
 * 따라서 RequestMessage는 application 패키지 아래에 위치하는 것이 적절합니다.
 */
@Getter
public class RequestMessage implements BaseMessage {
	private final String transactionId;
	private final String type = "request"; // Not final anymore, to allow flexibility if needed
	private final RequestBody body;
	private final Instant timestamp;

	// Constructor
	public RequestMessage(String transactionId, RequestBody body, Instant timestamp) {
		this.transactionId = transactionId;
		this.body = body;
		this.timestamp = timestamp;
	}

	@Override
	public String getTransactionId() {
		return this.transactionId;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public Instant getTimestamp() {
		return this.timestamp;
	}
}
