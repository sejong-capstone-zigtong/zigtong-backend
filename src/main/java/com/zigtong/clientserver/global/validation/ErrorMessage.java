package com.zigtong.clientserver.global.validation;

import static com.zigtong.clientserver.global.validation.Constant.*;

public class ErrorMessage {
	// 휴대폰 인증
	public final static String INVALID_VERIFICATION_CODE = "유효하지 않은 인증번호입니다.";

	public final static String INVALID_PHONE_NUMBER = "유효하지 않은 형식의 휴대폰 번호입니다.";
	public final static String EMPTY_PHONE_NUMBER = "휴대폰 번호를 입력해주세요.";
	public final static String EMPTY_VERIFICATION_CODE = "인증번호를 입력해주세요.";

	// 회원가입
	public final static String EMPTY_MEMBER_ACCOUNT = "아이디를 입력해주세요.";
	public final static String EMPTY_PASSWORD = "비밀번호를 입력해주세요.";
	public final static String EMPTY_PASSWORD_CHECK = "비밀번호 확인을 입력해주세요.";
	public final static String EMPTY_NAME = "이름을 입력해주세요.";
	public static final String EMPTY_BIRTHDATE = "생년월일을 입력해주세요.";
	public static final String EMPTY_NICKNAME = "닉네임을 입력해주세요.";
	public static final String EMPTY_ADDRESS = "주소를 입력해주세요.";

	public final static String INVALID_MEMBER_ACCOUNT =
		"아이디는 영문 소문자와 숫자로 이루어진 " + MEMBER_ACCOUNT_MIN_LENGTH + "자 이상, " + MEMBER_ACCOUNT_MAX_LENGTH
			+ "자 이하로 이루어져야 합니다.";
	public final static String INVALID_PASSWORD = "비밀번호는 영문 대소문자와 숫자로 이루어진 " + PASSWORD_MIN_LENGTH + "자 이상, "
		+ PASSWORD_MAX_LENGTH + "자 이하로 이루어져야 합니다.";
	public static final String INVALID_NICKNAME = "닉네임은 " + NICKNAME_MIN_LENGTH + "자 이상, " + NICKNAME_MAX_LENGTH
		+ "자 이하로 이루어져야 합니다.";

}
