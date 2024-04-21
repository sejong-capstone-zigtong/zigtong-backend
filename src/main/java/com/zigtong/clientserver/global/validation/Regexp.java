package com.zigtong.clientserver.global.validation;

import static com.zigtong.clientserver.global.validation.Constant.*;

public class Regexp {
	public static final String PHONE_NUMBER_REGEX = "^010[0-9]{8}$";

	public static final String VERIFICATION_CODE_REGEX = "^[0-9]{" + VERIFICATION_CODE_LENGTH + "}$";
}
