package com.zigtong.clientserver.domain.post.entity;

import java.time.LocalDateTime;

import com.zigtong.clientserver.domain.post.entity.type.RecruitmentStatus;
import com.zigtong.clientserver.domain.post.entity.type.WageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED")
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String content;

	private String title;

	private Long wage;

	@Column(columnDefinition = "VARCHAR(255)")
	private String address;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	@Column(columnDefinition = "VARCHAR(255)")
	private String category;

	private Integer numberOfApplicants;

	@Column(columnDefinition = "CHAR(11)")
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	private RecruitmentStatus recruitmentStatus;

	@Column(columnDefinition = "CHAR(36)", nullable = false)
	private String adminId;

	@Enumerated(EnumType.STRING)
	private WageType wageType;

	private LocalDateTime recruitmentStartTime;

	private LocalDateTime recruitmentEndTime;

	private LocalDateTime lunchTime;

	private Integer numberOfRecruits;
}
