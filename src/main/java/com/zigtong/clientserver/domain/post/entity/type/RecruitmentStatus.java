package com.zigtong.clientserver.domain.post.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RecruitmentStatus {
	RECRUITING("모집 중"),
	ENDED("모집 마감");

	private final String value;
}