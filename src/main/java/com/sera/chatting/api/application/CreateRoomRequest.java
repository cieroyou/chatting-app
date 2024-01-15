package com.sera.chatting.api.application;

import com.sera.chatting.api.usecase.dto.RoomCommand;
import jakarta.validation.constraints.NotBlank;

public record CreateRoomRequest(@NotBlank String name, String description, Integer maxParticipants) {
    public RoomCommand.CreateRoom toCommand() {
        return new RoomCommand.CreateRoom(name, description, maxParticipants);
    }
}
