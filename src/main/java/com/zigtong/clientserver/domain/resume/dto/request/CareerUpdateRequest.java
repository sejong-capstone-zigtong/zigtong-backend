package com.zigtong.clientserver.domain.resume.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CareerUpdateRequest(
	@NotBlank
	String company,
	@NotBlank
	String role,
	@NotBlank
	String roleDetail,
	@NotNull
	LocalDate startDate,
	@NotNull
	LocalDate endDate
) {
}
