package com.sera.chatting.common.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/*
 방을 생성한 사람이 기본으로 방장이 되고, 바로 채팅방에 입장하여 채팅을 할 수 있다.
 방은 최대 참석인원수를 가진다.

 */
@Getter
@Entity
@Table(name = "chatting_room")
@NoArgsConstructor
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chatting_room_id", nullable = false, unique = true)
    String chattingRoomId;
    String name;
    String description;


    @Builder
    private ChattingRoom(String name, String description) {
        this.chattingRoomId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }


    public static ChattingRoom createRoom(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        return ChattingRoom.builder()
                .name(name)
                .description(description)
                .build();
    }
}
