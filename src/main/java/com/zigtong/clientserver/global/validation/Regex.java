package com.zigtong.clientserver.global.validation;

import static com.zigtong.clientserver.global.validation.Constant.*;

public final class Regex {
	public static final String PHONE_NUMBER_REGEX = "^010[0-9]{8}$";
	public static final String VERIFICATION_CODE_REGEX = "^[0-9]{" + VERIFICATION_CODE_LENGTH + "}$";
	public static final String MEMBER_ACCOUNT_REGEX =
		"^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{" + MEMBER_ACCOUNT_MIN_LENGTH + "," + MEMBER_ACCOUNT_MAX_LENGTH + "}$";
	public static final String PASSWORD_REGEX =
		"^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{" + PASSWORD_MIN_LENGTH + "," + PASSWORD_MAX_LENGTH + "}$";
	public static final String NICKNAME_REGEX =
		"^[a-zA-Z0-9가-힣]{" + NICKNAME_MIN_LENGTH + "," + NICKNAME_MAX_LENGTH + "}$";
}
