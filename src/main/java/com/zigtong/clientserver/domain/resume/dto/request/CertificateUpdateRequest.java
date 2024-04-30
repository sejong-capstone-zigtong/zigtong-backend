package com.zigtong.clientserver.domain.resume.dto.request;

import java.util.List;

public record CertificateUpdateRequest(
	List<Integer> ids
) {
}
