package com.zigtong.clientserver.domain.resume.entity;

import com.zigtong.clientserver.domain.resume.dto.request.CareerUpdateRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Career {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED")
	private Integer id;

	private String companyName;
	private String role;
	private String roleDetail;
	private Integer months;

	@ManyToOne(fetch = FetchType.LAZY)
	private Resume resume;

	@Builder(access = AccessLevel.PRIVATE)
	private Career(Resume resume, String companyName, String role, String roleDetail, Integer months) {
		this.resume = resume;
		this.companyName = companyName;
		this.role = role;
		this.roleDetail = roleDetail;
		this.months = months;
	}

	public static Career create(Resume resume, CareerUpdateRequest request) {
		return Career.builder()
			.resume(resume)
			.companyName(request.company())
			.role(request.role())
			.roleDetail(request.roleDetail())
			.months(request.months())
			.build();
	}
}
