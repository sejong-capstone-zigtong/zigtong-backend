package com.zigtong.clientserver.domain.worker.service;

import static com.zigtong.clientserver.error.ErrorCode.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zigtong.clientserver.domain.auth.service.AuthService;
import com.zigtong.clientserver.domain.resume.entity.Resume;
import com.zigtong.clientserver.domain.resume.repository.ResumeRepository;
import com.zigtong.clientserver.domain.worker.dto.request.WorkerSignUpRequest;
import com.zigtong.clientserver.domain.worker.entity.Worker;
import com.zigtong.clientserver.domain.worker.repository.WorkerRepository;
import com.zigtong.clientserver.error.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkerService {
	private final AuthService authService;

	private final WorkerRepository workerRepository;
	private final ResumeRepository resumeRepository;
	private final PasswordEncoder passwordEncoder;

	public void signUp(WorkerSignUpRequest request) {
		validateEnableSignUp(request);

		String encryptedPassword = passwordEncoder.encode(request.password());

		Worker worker = Worker.create(request, encryptedPassword);
		workerRepository.save(worker);

		Resume resume = Resume.create(worker);
		resumeRepository.save(resume);
	}

	private void validateEnableSignUp(WorkerSignUpRequest request) {
		//authService.validateVerificationSession(session);

		if (workerRepository.existsByPhoneNumber(request.phoneNumber())) {
			throw new CustomException(DUPLICATED_PHONE_NUMBER);
		}

		if (workerRepository.existsByMemberAccount(request.memberAccount())) {
			throw new CustomException(DUPLICATED_MEMBER_ACCOUNT);
		}

		if (workerRepository.existsByNickname(request.nickname())) {
			throw new CustomException(DUPLICATED_NICKNAME);
		}
	}
}
