package com.zigtong.clientserver.domain.resume.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zigtong.clientserver.domain.certificate.entity.Certificate;
import com.zigtong.clientserver.domain.certificate.repository.CertificateRepository;
import com.zigtong.clientserver.domain.resume.dto.request.CareerUpdateRequest;
import com.zigtong.clientserver.domain.resume.dto.request.CertificateUpdateRequest;
import com.zigtong.clientserver.domain.resume.dto.request.StatementUpdateRequest;
import com.zigtong.clientserver.domain.resume.dto.response.ProfileImageUrlResponse;
import com.zigtong.clientserver.domain.resume.entity.Career;
import com.zigtong.clientserver.domain.resume.entity.Resume;
import com.zigtong.clientserver.domain.resume.repository.ResumeRepository;
import com.zigtong.clientserver.infra.s3.service.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeService {
	private final S3Service s3Service;

	private final ResumeRepository resumeRepository;

	private final CertificateRepository certificateRepository;

	public void updateCertificates(CertificateUpdateRequest request, String workerId) {
		Resume resume = resumeRepository.findById(workerId)
			.orElseThrow();
		List<Certificate> certificates = certificateRepository.findAllById(request.ids());

		resume.updateCertificates(certificates);

		resumeRepository.save(resume);
	}

	public void updateStatement(StatementUpdateRequest request, String workerId) {
		Resume resume = resumeRepository.findById(workerId)
			.orElseThrow();

		resume.updateStatement(request.statement());

		resumeRepository.save(resume);
	}

	public void updateCareer(List<CareerUpdateRequest> requests, String workerId) {
		Resume resume = resumeRepository.findById(workerId)
			.orElseThrow();

		List<Career> careers = requests.stream()
			.map(request -> Career.create(resume, request))
			.collect(Collectors.toUnmodifiableList());

		resume.updateCareers(careers);

		resumeRepository.save(resume);
	}

	@SneakyThrows
	public void uploadProfileImage(String workerId, MultipartFile profileImage) {
		Resume resume = resumeRepository.findById(workerId)
			.orElseThrow();

		s3Service.uploadProfileImage(workerId, profileImage);

		resume.updateProfileImageUrl("https://zigtong-profile-image.s3.ap-northeast-2.amazonaws.com/" + workerId);
	}

	public ProfileImageUrlResponse getProfileImageUploadedUrl(String workerId) {
		Resume resume = resumeRepository.findById(workerId)
			.orElseThrow();

		return new ProfileImageUrlResponse(resume.getUploadedUrl());
	}
}
