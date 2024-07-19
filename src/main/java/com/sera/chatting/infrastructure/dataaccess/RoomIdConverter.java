package com.sera.chatting.infrastructure.dataaccess;

import java.util.UUID;

import com.sera.chatting.common.domain.valueobject.RoomId;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoomIdConverter implements AttributeConverter<RoomId, String> {

	@Override
	public String convertToDatabaseColumn(RoomId attribute) {
		return attribute == null ? null : attribute.getValue().toString();
	}

	@Override
	public RoomId convertToEntityAttribute(String dbData) {
		return dbData == null ? null : new RoomId(UUID.fromString(dbData));
	}

}