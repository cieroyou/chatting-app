package com.sera.chatting.api.application;

import com.sera.chatting.api.usecase.ChattingRoomUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/chatting-room")
public class ChattingRoomController {

    private final ChattingRoomUsecase chattingRoomService;

    @PostMapping
    public String createChattingRoom(@RequestBody @Valid CreateRoomRequest createRoomRequest) {
        return chattingRoomService.createRoom(createRoomRequest.toCommand());
    }
}
