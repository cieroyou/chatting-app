package com.sera.chatting.domain;

import static lombok.AccessLevel.*;

import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.common.domain.valueobject.UserId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "participants")
@NoArgsConstructor(access = PRIVATE)
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private UserId userId;
	private RoomId chattingRoomId;

	public Participant(UserId userId, RoomId chattingRoomId) {
		this.userId = userId;
		this.chattingRoomId = chattingRoomId;
	}
}
