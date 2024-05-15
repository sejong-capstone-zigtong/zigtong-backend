package com.zigtong.clientserver.domain.certificate.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zigtong.clientserver.domain.certificate.dto.response.CertificateAffiliationResponse;
import com.zigtong.clientserver.domain.certificate.dto.response.CertificateInfoResponse;
import com.zigtong.clientserver.domain.certificate.entity.Certificate;
import com.zigtong.clientserver.domain.certificate.repository.CertificateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificateService {
	private final CertificateRepository certificateRepository;

	@Transactional(readOnly = true)
	public List<CertificateInfoResponse> getCertificateByAffiliation(String affiliationName) {
		List<Certificate> certificationList = certificateRepository.findAllByAffiliation(affiliationName);

		return certificationList.stream()
			.map(CertificateInfoResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}

	@Transactional(readOnly = true)
	public CertificateAffiliationResponse getCertificateAffiliation() {
		List<String> affiliations = certificateRepository.findAllAffiliation();

		return new CertificateAffiliationResponse(affiliations);
	}
}
