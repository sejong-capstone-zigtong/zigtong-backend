package com.zigtong.clientserver.domain.resume.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zigtong.clientserver.domain.certificate.entity.Certificate;
import com.zigtong.clientserver.domain.certificate.repository.CertificateRepository;
import com.zigtong.clientserver.domain.resume.dto.request.CareerUpdateRequest;
import com.zigtong.clientserver.domain.resume.dto.request.CertificateUpdateRequest;
import com.zigtong.clientserver.domain.resume.dto.request.StatementUpdateRequest;
import com.zigtong.clientserver.domain.resume.entity.Career;
import com.zigtong.clientserver.domain.resume.entity.Resume;
import com.zigtong.clientserver.domain.resume.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeService {
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
}
