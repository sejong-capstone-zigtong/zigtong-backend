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

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ENDPOINT_PREFIX + "/auth")
public class AuthController {
	private final AuthService authService;

	@Operation(summary = "인증 코드 전송", description = "인증 코드를 전송합니다. 인증 코드는 6자리 숫자입니다. 예시: 123456")
	@PostMapping("/send-code")
	public VerificationCodeMessageResponse sendVerificationCode(@RequestBody VerificationCodeMessageRequest request,
		HttpServletRequest servletRequest) throws
		NurigoMessageNotReceivedException,
		NurigoEmptyResponseException,
		NurigoUnknownException {
		HttpSession session = servletRequest.getSession();

		return authService.sendVerificationCode(request, session);
	}

	@Operation(summary = "인증 코드 확인", description = "인증 코드를 확인합니다.")
	@PostMapping("/verify")
	public VerificationResponse verifyCode(@RequestBody VerificationRequest request,
		HttpServletRequest servletRequest) {

		HttpSession session = servletRequest.getSession();

		return authService.verify(request, session);
	}

	@Operation(summary = "로그인", description = "로그인을 진행 후, Access Token을 발급받습니다.")
	@PostMapping("/sign-in")
	public SignInResponse signIn(@RequestBody SignInRequest request) {
		return authService.signIn(request);
	}
}
