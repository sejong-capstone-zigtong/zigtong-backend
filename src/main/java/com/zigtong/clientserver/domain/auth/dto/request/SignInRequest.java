package com.zigtong.clientserver.domain.auth.dto.request;

import static com.zigtong.clientserver.global.validation.ErrorMessage.*;
import static com.zigtong.clientserver.global.validation.Regex.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignInRequest(
	@NotBlank(message = EMPTY_MEMBER_ACCOUNT)
	@Pattern(regexp = MEMBER_ACCOUNT_REGEX, message = INVALID_MEMBER_ACCOUNT)
	String memberAccount,
	@NotBlank(message = EMPTY_PASSWORD)
	@Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD)
	String password) {
}
