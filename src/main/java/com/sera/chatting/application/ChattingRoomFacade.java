package com.sera.chatting.application;

import org.springframework.stereotype.Service;

import com.sera.chatting.application.dto.RoomCommand;
import com.sera.chatting.common.domain.valueobject.RoomId;
import com.sera.chatting.common.domain.valueobject.UserId;
import com.sera.chatting.domain.ChattingRoom;
import com.sera.chatting.domain.ChattingRoomService;
import com.sera.chatting.domain.Participant;
import com.sera.chatting.infrastructure.interfaces.ChattingRoomReader;
import com.sera.chatting.infrastructure.interfaces.ChattingRoomStore;
import com.sera.chatting.infrastructure.interfaces.ParticipantReader;
import com.sera.chatting.infrastructure.interfaces.ParticipantStore;
import com.sera.chatting.presentation.websocket.dto.CreateRoomResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChattingRoomFacade {

	private final ChattingRoomStore roomStore;
	private final ChattingRoomReader roomReader;
	private final ParticipantReader participantReader;
	private final ParticipantStore participantStore;
	private final ChattingRoomService roomService;

	/**
	 * 방 생성 후 방 id를 반환
	 */
	public CreateRoomResponse createRoom(RoomCommand.CreateRoom createRoom) {
		var room = ChattingRoom.createRoom(createRoom.name(), createRoom.maxParticipants(), createRoom.description());
		roomStore.save(room);
		return new CreateRoomResponse(room.getChattingRoomId().getValue());
	}

	public RoomDetail getRoom(RoomId roomId) {
		var room = roomReader.readByChattingRoomId(roomId);
		var participants = participantReader.readByChattingRoomId(roomId);
		return new RoomDetail(room, participants);

	}
	/**
	 * 방 입장 요청
	 */
	@Transactional
	public void addParticipant(RoomId roomId, UserId userId) {
		var room = roomReader.readByChattingRoomId(roomId);
		var participants = participantReader.readByChattingRoomId(roomId);
		roomService.addParticipant(room, userId);
		participantStore.save(new Participant(userId, roomId));
	}

}
