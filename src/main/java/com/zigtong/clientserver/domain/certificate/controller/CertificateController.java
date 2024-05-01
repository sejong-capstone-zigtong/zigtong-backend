package com.zigtong.clientserver.domain.certificate.controller;

import static com.zigtong.clientserver.global.constant.EndpointConstant.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zigtong.clientserver.domain.certificate.dto.response.CertificateInfoResponse;
import com.zigtong.clientserver.domain.certificate.service.CertificateService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ENDPOINT_PREFIX + "/certificate")
public class CertificateController {
	private final CertificateService certificationService;

	@Operation(summary = "자격증 카테고리 조회", description = "자격증 카테고리를 조회합니다.")
	@GetMapping("/affiliation")
	public List<String> getCertificateCategories() {
		return certificationService.getCertificateAffiliation();
	}

	@Operation(summary = "자격증 정보 조회", description = "자격증 카테고리에 해당하는 자격증을 조회합니다.")
	@GetMapping("/list")
	public List<CertificateInfoResponse> getCertificationInfos(@RequestParam String affiliation) {
		return certificationService.getCertificateByAffiliation(affiliation);
	}
}
