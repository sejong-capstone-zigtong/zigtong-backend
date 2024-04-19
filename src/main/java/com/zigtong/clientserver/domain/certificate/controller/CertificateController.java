package com.zigtong.clientserver.domain.certificate.controller;

import static com.zigtong.clientserver.global.constant.EndpointConstant.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zigtong.clientserver.domain.certificate.dto.response.CertificateInfoResponse;
import com.zigtong.clientserver.domain.certificate.service.CertificateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ENDPOINT_PREFIX + "/certificate")
public class CertificateController {
	private final CertificateService certificationService;

	@GetMapping("/affiliation")
	public List<String> getCertificateCategories() {
		return certificationService.getCertificateAffiliation();
	}

	@GetMapping("/list")
	public List<CertificateInfoResponse> getCertificationInfos(@RequestParam String affiliation) {
		return certificationService.getCertificateByAffiliation(affiliation);
	}
}
