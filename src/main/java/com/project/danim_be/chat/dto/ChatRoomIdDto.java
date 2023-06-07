package com.project.danim_be.chat.dto;

import com.project.danim_be.chat.entity.ChatRoom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomIdDto {

	private String RoomId;

	public ChatRoomIdDto(ChatRoom chatRoom) {
		this.RoomId=chatRoom.getRoomId();
	}


}