package com.zigtong.clientserver.domain.certificate.dto.response;

import com.zigtong.clientserver.domain.certificate.entity.Certificate;

public record CertificateInfoResponse(String affiliation, String item) {
	public static CertificateInfoResponse from(Certificate certificate) {
		return new CertificateInfoResponse(
			certificate.getAffiliation(),
			certificate.getItem()
		);
	}

}