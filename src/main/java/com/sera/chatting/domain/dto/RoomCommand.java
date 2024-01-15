package com.sera.chatting.domain.dto;

public class RoomCommand {
    public record CreateRoom(String name, String description, Integer maxParticipants) {
    }
}
