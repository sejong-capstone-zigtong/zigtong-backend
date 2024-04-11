package com.zigtong.clientserver.domain.auth.dto.request;

import static com.zigtong.clientserver.domain.auth.validation.ValidationConstant.*;
import static com.zigtong.clientserver.domain.auth.validation.ValidationMessage.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerificationRequest(
	@NotBlank(message = EMPTY_PHONE_NUMBER)
	@Pattern(regexp = PHONE_NUMBER_REGEX, message = INVALID_PHONE_NUMBER)
	String receiver,
	@NotBlank(message = EMPTY_VERIFICATION_CODE)
	String verificationCode) {
}