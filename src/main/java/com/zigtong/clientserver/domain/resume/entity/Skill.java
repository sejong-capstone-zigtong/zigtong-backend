package com.zigtong.clientserver.domain.resume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Skill {
	@Id
	private String id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private Resume resume;
}
