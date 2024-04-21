package com.zigtong.clientserver.domain.auth.dto.request;

import static com.zigtong.clientserver.global.validation.ErrorMessage.*;
import static com.zigtong.clientserver.global.validation.Regex.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerificationRequest(
	@NotBlank(message = EMPTY_PHONE_NUMBER)
	@Pattern(regexp = PHONE_NUMBER_REGEX, message = INVALID_PHONE_NUMBER)
	String receiver,
	@NotBlank(message = EMPTY_VERIFICATION_CODE)
	@Pattern(regexp = VERIFICATION_CODE_REGEX, message = INVALID_VERIFICATION_CODE)
	String verificationCode) {
}
