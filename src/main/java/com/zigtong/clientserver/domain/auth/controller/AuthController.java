package com.zigtong.clientserver.domain.auth.controller;

import static com.zigtong.clientserver.global.constant.EndpointConstant.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;

import com.zigtong.clientserver.domain.auth.dto.request.SignInRequest;
import com.zigtong.clientserver.domain.auth.dto.request.VerificationCodeMessageRequest;
import com.zigtong.clientserver.domain.auth.dto.request.VerificationRequest;
import com.zigtong.clientserver.domain.auth.dto.response.SignInResponse;
import com.zigtong.clientserver.domain.auth.dto.response.VerificationCodeMessageResponse;
import com.zigtong.clientserver.domain.auth.response.VerificationResponse;
import com.zigtong.clientserver.domain.auth.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ENDPOINT_PREFIX + "/auth")
public class AuthController {
	private final AuthService authService;

	@PostMapping("/send-code")
	public VerificationCodeMessageResponse sendVerificationCode(@RequestBody VerificationCodeMessageRequest request,
		HttpServletRequest servletRequest) throws
		NurigoMessageNotReceivedException,
		NurigoEmptyResponseException,
		NurigoUnknownException {
		HttpSession session = servletRequest.getSession();

		return authService.sendVerificationCode(request, session);
	}

	@PostMapping("/verify")
	public VerificationResponse verifyCode(@RequestBody VerificationRequest request,
		HttpServletRequest servletRequest) {

		HttpSession session = servletRequest.getSession();

		return authService.verify(request, session);
	}

	@PostMapping("/sign-in")
	public SignInResponse signIn(@RequestBody SignInRequest request) {
		return authService.signIn(request);
	}
}
