package com.sera.chatting.domain;

import static lombok.AccessLevel.*;

import java.util.UUID;

import org.springframework.util.StringUtils;

import com.sera.chatting.common.domain.valueobject.RoomId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 방을 생성한 사람이 기본으로 방장이 되고, 바로 채팅방에 입장하여 채팅을 할 수 있다.
 방은 최대 참석인원수를 가진다.

 */
@Getter
@Entity
@Table(name = "chatting_room")
@NoArgsConstructor(access = PRIVATE)
public class ChattingRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "chatting_room_id", nullable = false, unique = true)
	RoomId chattingRoomId;
	String name;
	@Column(name = "max_participants", nullable = false, unique = true)
	Integer maxParticipants;
	String description;

	@Builder
	private ChattingRoom(String name, Integer maxParticipants, String description) {
		if (!StringUtils.hasText(name)) {
			throw new IllegalArgumentException("name is blank");
		}
		if (maxParticipants == null) {
			throw new IllegalArgumentException("maxParticipants is null");
		}
		this.chattingRoomId = new RoomId(UUID.randomUUID());
		this.name = name;
		this.maxParticipants = maxParticipants;
		this.description = description;
	}

	public static ChattingRoom createRoom(String name, Integer maxParticipants, String description) {
		return ChattingRoom.builder()
			.name(name)
			.maxParticipants(maxParticipants)
			.description(description)
			.build();
	}
}
