package com.zigtong.clientserver.domain.post.entity.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RecruitmentStatus {
	RECRUITING("모집 중"),
	ENDED("모집 마감");

	private final String value;
}