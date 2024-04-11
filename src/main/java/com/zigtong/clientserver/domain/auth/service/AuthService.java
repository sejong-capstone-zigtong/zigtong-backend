package com.zigtong.clientserver.domain.auth.service;

import static com.zigtong.clientserver.domain.auth.validation.ValidationConstant.*;
import static com.zigtong.clientserver.error.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;

import com.zigtong.clientserver.domain.auth.dto.request.VerificationCodeMessageRequest;
import com.zigtong.clientserver.domain.auth.dto.request.VerificationRequest;
import com.zigtong.clientserver.domain.auth.dto.response.VerificationCodeMessageResponse;
import com.zigtong.clientserver.domain.auth.response.VerificationResponse;
import com.zigtong.clientserver.error.exception.CustomException;
import com.zigtong.clientserver.infra.sms.SmsService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
	private static final String VERIFICATION_CODE_MESSAGE = "본인확인 인증번호 [%s]를 화면에 입력해주세요.";

	private static final String VERIFICATION_CODE_SESSION_ATTRIBUTE = "verificationCode";
	private static final int SESSION_TIME_OUT_SEC = 60 * 5; // 5분

	private final SmsService smsService;

	public VerificationCodeMessageResponse sendVerificationCode(VerificationCodeMessageRequest request,
		HttpSession session) throws
		NurigoMessageNotReceivedException,
		NurigoEmptyResponseException,
		NurigoUnknownException {
		String verificationCode = generateVerifyCode();

		setVerificationSession(verificationCode, session);
		smsService.sendMessage(request.receiver(), String.format(VERIFICATION_CODE_MESSAGE, verificationCode));

		return VerificationCodeMessageResponse.success();
	}

	private String generateVerifyCode() {
		return String.valueOf((int)(Math.random() * Math.pow(10, VERIFICATION_CODE_LENGTH)));
	}

	private void setVerificationSession(String verifyCode, HttpSession session) {
		session.setMaxInactiveInterval(SESSION_TIME_OUT_SEC);
		session.setAttribute(VERIFICATION_CODE_SESSION_ATTRIBUTE, verifyCode);
	}

	public VerificationResponse verify(VerificationRequest request, HttpSession session) {
		String sessionVerifyCode = (String)session.getAttribute(VERIFICATION_CODE_SESSION_ATTRIBUTE);

		if (sessionVerifyCode == null) {
			throw new CustomException(EXPIRED_VERIFICATION_CODE);
		}

		if (!sessionVerifyCode.equals(request.verificationCode())) {
			throw new CustomException(INVALID_VERIFICATION_CODE);
		}

		session.invalidate();    // 세션 무효화

		return VerificationResponse.success();
	}
}
