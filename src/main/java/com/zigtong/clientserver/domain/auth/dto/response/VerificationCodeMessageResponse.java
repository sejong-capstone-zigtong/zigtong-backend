package com.zigtong.clientserver.domain.auth.dto.response;

public record VerificationCodeMessageResponse(Boolean isSuccess, String errorMessage) {
	public static VerificationCodeMessageResponse success() {
		return new VerificationCodeMessageResponse(true, null);
	}

	public static VerificationCodeMessageResponse fail(String errorMessage) {
		return new VerificationCodeMessageResponse(false, errorMessage);
	}
}
