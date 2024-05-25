package com.zigtong.clientserver.domain.post.entity.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WageType {
	CASE("건당"),
	DAY("일급"),
	MONTH("월급");

	private final String value;
}