package com.zigtong.clientserver.domain.resume.controller;

import static com.zigtong.clientserver.global.constant.EndpointConstant.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zigtong.clientserver.domain.resume.dto.request.CareerUpdateRequest;
import com.zigtong.clientserver.domain.resume.dto.request.CertificateUpdateRequest;
import com.zigtong.clientserver.domain.resume.dto.request.StatementUpdateRequest;
import com.zigtong.clientserver.domain.resume.dto.response.ProfileImageUrlResponse;
import com.zigtong.clientserver.domain.resume.service.ResumeService;
import com.zigtong.clientserver.security.util.SecurityContextUtil;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX + "/resume")
public class ResumeController {
	private final ResumeService resumeService;

	@Operation(summary = "프로필 이미지 업로드", description = "이력서의 프로필 이미지를 업로드합니다.")
	@PutMapping("/profile-image")
	public void uploadProfileImage(@RequestPart(value = "profileImage") MultipartFile profileImage) {
		String workerId = SecurityContextUtil.extractWorkerId();

		resumeService.uploadProfileImage(workerId, profileImage);
	}

	@Operation(summary = "프로필 이미지 URL 조회", description = "이력서의 프로필 이미지 URL을 조회합니다.")
	@GetMapping("/profile-image")
	public ProfileImageUrlResponse getProfileImageUploadedUrl() {
		String workerId = SecurityContextUtil.extractWorkerId();

		return resumeService.getProfileImageUploadedUrl(workerId);
	}

	@Operation(summary = "자격증 정보 수정", description = "이력서의 자격증 정보를 수정합니다.")
	@PutMapping("/certificate")
	public void updateCertificates(@RequestBody CertificateUpdateRequest request) {
		String workerId = SecurityContextUtil.extractWorkerId();

		resumeService.updateCertificates(request, workerId);
	}

	@Operation(summary = "한줄소개 수정", description = "이력서의 한줄소개를 수정합니다.")
	@PutMapping("/statement")
	public void updateStatement(@RequestBody StatementUpdateRequest request) {
		String workerId = SecurityContextUtil.extractWorkerId();

		resumeService.updateStatement(request, workerId);
	}

	@Operation(summary = "경력 수정", description = "이력서의 경력을 수정합니다.")
	@PutMapping("/career")
	public void updateCareer(@RequestBody List<CareerUpdateRequest> requests) {
		String workerId = SecurityContextUtil.extractWorkerId();

		resumeService.updateCareer(requests, workerId);
	}

}