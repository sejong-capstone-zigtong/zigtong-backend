package com.zigtong.clientserver.security.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.zigtong.clientserver.global.constant.EndpointConstant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PublicUris {
	SIGN_IN("/auth/sign-in"),
	SIGN_UP("/member/sign-up"),
	SEND_CODE("/auth/send-code"),
	VERIFY("/auth/verify");

	private final String value;

	public static List<String> getAllUrisWithEndpointPrefix() {
		return Arrays.stream(PublicUris.values())
			.map(PublicUris::getUriWithEndpointPrefix)
			.collect(Collectors.toUnmodifiableList());
	}

	private String getUriWithEndpointPrefix() {
		return EndpointConstant.ENDPOINT_PREFIX + value;
	}
}
