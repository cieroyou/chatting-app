package com.sera.chatting.infrastructure.dataaccess;

import java.util.UUID;

import com.sera.chatting.common.domain.valueobject.UserId;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserIdConverter implements AttributeConverter<UserId, String> {

	// userId: e1c2a416-23c9-457f-9a0a-b6b0937230c3
	@Override
	public String convertToDatabaseColumn(UserId attribute) {
		return attribute == null ? null : attribute.getValue().toString();
	}

	@Override
	public UserId convertToEntityAttribute(String dbData) {
		return dbData == null ? null : new UserId(UUID.fromString(dbData));
	}
}