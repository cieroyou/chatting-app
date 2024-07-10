package com.sera.chatting.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AckBody<T> implements MessageBody {
	Result result;
	String errorCode;
	String message;
	T data;

	public static <T> AckBody<T> success(T data, String message) {
		return (AckBody<T>)AckBody.builder()
			.result(Result.SUCCESS)
			.data(data)
			.message(message)
			.build();
	}

	public static <T> AckBody<T> success(T data) {
		return success(data, null);
	}

	public static AckBody fail(String message, String errorCode) {
		return AckBody.builder()
			.result(Result.FAIL)
			.message(message)
			.errorCode(errorCode)
			.build();
	}

	public static AckBody fail(String errorCode) {
		return AckBody.builder()
			.result(Result.FAIL)
			// .message(errorCode.getErrorMsg())
			// .errorCode(errorCode.name())
			.build();
	}

	public enum Result {
		SUCCESS, FAIL
	}
}
