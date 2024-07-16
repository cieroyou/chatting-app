package com.sera.chatting.application;

import org.springframework.stereotype.Service;

import com.sera.chatting.application.dto.RoomCommand;
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
		return new CreateRoomResponse(room.getChattingRoomId());
	}

	/**
	 * 방 입장 요청
	 */
	@Transactional
	public void addParticipant(String roomId, String userId) {
		var room = roomReader.readByChattingRoomId(roomId);
		var participants = participantReader.readByChattingRoomId(roomId);
		roomService.addParticipant(room, participants, userId);
		participantStore.save(new Participant(userId, room.getChattingRoomId()));
	}

	// @Transactional
	// public void assignParticipantToRoom(Long roomId, Long participantId) {
	// 	Room room = roomRepository.findById(roomId)
	// 		.orElseThrow(() -> new IllegalArgumentException("Room not found"));
	//
	// 	Participant participant = participantRepository.findById(participantId)
	// 		.orElseThrow(() -> new IllegalArgumentException("Participant not found"));
	//
	// 	roomService.assignParticipantToRoom(room, participant);
	// 	participantRepository.save(participant); // 필요 시 업데이트
	// 	roomRepository.save(room); // 필요 시 업데이트
	// }
}
