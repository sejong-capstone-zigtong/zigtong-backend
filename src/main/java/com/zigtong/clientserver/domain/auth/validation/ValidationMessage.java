package com.zigtong.clientserver.domain.auth.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationMessage {
	public static final String INVALID_PHONE_NUMBER = "유효하지 않은 휴대폰 번호입니다.";
	public static final String EMPTY_PHONE_NUMBER = "휴대폰 번호를 입력해주세요.";
	public static final String EMPTY_VERIFICATION_CODE = "인증번호를 입력해주세요.";

}
