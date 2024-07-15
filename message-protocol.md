# 방 생성 요청

```json
{
  "type": "request",
  "transaction_id": "123444425",
  "session_id": "absdf",
  "body": {
    "command": "create_room",
    "room_name": "채팅방"
  }
}
```

# 성공응답

```json
{
  "type": "ack",
  "transaction_id": "12332342",
  "body": {
    "result": "success",
    "data": {}
  },
  "timestamp": "2024-01-012T12:30:00"
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