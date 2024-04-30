package com.zigtong.clientserver.domain.resume.controller;

import static com.zigtong.clientserver.global.constant.EndpointConstant.*;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigtong.clientserver.domain.resume.dto.request.CertificateUpdateRequest;
import com.zigtong.clientserver.domain.resume.dto.request.StatementUpdateRequest;
import com.zigtong.clientserver.domain.resume.service.ResumeService;
import com.zigtong.clientserver.security.util.SecurityContextUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX + "/resume")
public class ResumeController {
	private final ResumeService resumeService;

	@PutMapping("/profile-image")
	public void uploadProfileImage() {

	}

	@PutMapping("/certificate")
	public void updateCertificates(@RequestBody CertificateUpdateRequest request) {
		String workerId = SecurityContextUtil.extractWorkerId();

		resumeService.updateCertificates(request, workerId);
	}

	@PutMapping("/statement")
	public void updateStatement(@RequestBody StatementUpdateRequest request) {
		String workerId = SecurityContextUtil.extractWorkerId();

		resumeService.updateStatement(request, workerId);
	}

	@PutMapping("/career")
	public void updateCareer() {

	}

}