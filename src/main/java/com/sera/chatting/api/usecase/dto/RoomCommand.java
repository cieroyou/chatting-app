package com.sera.chatting.api.usecase.dto;

public class RoomCommand {
    public record CreateRoom(String name, String description, Integer maxParticipants) {
    }
}
