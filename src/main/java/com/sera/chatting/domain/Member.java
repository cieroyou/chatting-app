package com.sera.chatting.domain;

import static lombok.AccessLevel.*;

import java.util.UUID;

import com.sera.chatting.common.domain.valueobject.UserId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "member")
@Entity
@NoArgsConstructor(access = PRIVATE)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false, unique = true)
	private UserId userId;
	private String username;

	public Member(String username) {
		this.userId = new UserId(UUID.randomUUID());
		this.username = username;
	}
}
