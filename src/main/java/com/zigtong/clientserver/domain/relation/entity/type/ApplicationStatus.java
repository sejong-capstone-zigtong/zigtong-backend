package com.zigtong.clientserver.domain.relation.entity.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApplicationStatus {
	PENDING("매칭 중"),
	ACCEPTED("수락됨"),
	REJECTED("거절됨");

	private final String description;
}
