package com.zigtong.clientserver.domain.auth.dto.request;

import static com.zigtong.clientserver.domain.auth.validation.ValidationMessage.*;
import static com.zigtong.clientserver.global.validation.Regexp.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerificationCodeMessageRequest(
	@NotBlank(message = EMPTY_PHONE_NUMBER)
	@Pattern(regexp = PHONE_NUMBER_REGEX, message = INVALID_PHONE_NUMBER)
	String receiver) {
}
