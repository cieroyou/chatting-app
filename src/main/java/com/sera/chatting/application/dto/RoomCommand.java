package com.sera.chatting.application.dto;

public class RoomCommand {
    public record CreateRoom(String name, String description, Integer maxParticipants) {
    }
}
