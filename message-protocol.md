# 방 생성 요청

```json
{
  "type": "request",
  "transaction_id": "123444425",
  "body": {
    "command": "create_room",
    "room_name": "채팅방"
  }
}
```

# 성공응답

```json
{
  "transaction_id": "123444425",
  "type": "ack ",
  "body": {
    "result": "SUCCESS",
    "error_code": null,
    "message": null,
    "data": {
      "room_id": "ea87e697-6829-4630-aecb-93e37f1745ab"
    }
  },
  "timestamp": "2024-07-15T07:14:22.806824Z"
}
```

# 실패응답

```json
{
  "type": "ack",
  "transaction_id": "12332342",
  "body": {
    "result": "fail",
    "error_code": "ANB01",
    "message": "메세지가 성공적으로 전송됐습니다"
  },
  "timestamp": "2024-01-012T12:30:00"
}
```