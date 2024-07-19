package com.sera.chatting.presentation.websocket.dto.common;

import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * # 성공응답
 * ```json
 * {
 * 	"type": "ack",
 * 	"transaction_id": "12332342",
 * 	"body": {
 * 		"result": "success",
 * 		"data": {}
 *        },
 * 	"timestamp": "2024-01-012T12:30:00"
 * }
 * ```
 *
 * # 실패응답
 * ```json
 * {
 * 	"type": "ack",
 * 	"transaction_id": "12332342",
 * 	"body": {
 * 		"result": "fail",
 * 		"error_code": "ANB01",
 * 		"message": "메세지가 성공적으로 전송됐습니다"
 *    },
 * 	"timestamp": "2024-01-012T12:30:00"
 * }
 */
@NoArgsConstructor
@Getter
public class AckMessage implements BaseMessage {
	private String transactionId;
	private final MessageType type = MessageType.ACK; // Not final anymore, to allow flexibility if needed
	private AckBody body;
	private Instant timestamp;

	public AckMessage(String transactionId, AckBody body) {
		this.transactionId = transactionId;
		this.body = body;
		this.timestamp = Instant.now();
	}

	@Override
	public String getTransactionId() {
		return this.transactionId;
	}

	@Override
	public MessageType getType() {
		return this.type;
	}

	@Override
	public Instant getTimestamp() {
		return this.timestamp;
	}
}
