package com.zigtong.clientserver.domain.post.dto;

import java.time.LocalDateTime;

import com.zigtong.clientserver.domain.post.entity.Post;
import com.zigtong.clientserver.domain.post.entity.type.RecruitmentStatus;
import com.zigtong.clientserver.domain.post.entity.type.WageType;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record PostDetailResponse(
	Long id,
	String content,
	String title,
	Long wage,
	String address,
	LocalDateTime startTime,
	LocalDateTime endTime,
	String category,
	Integer numberOfApplicants,
	String phoneNumber,
	RecruitmentStatus recruitmentStatus,
	String adminId,
	WageType wageType
) {
	public static PostDetailResponse from(Post post) {
		return builder()
			.id(post.getId())
			.content(post.getContent())
			//.title(post.getTitle())
			.title("")
			.wage(post.getWage())
			.address(post.getAddress())
			.startTime(post.getStartTime())
			.endTime(post.getEndTime())
			.category(post.getCategory())
			.numberOfApplicants(post.getNumberOfApplicants())
			.phoneNumber(post.getPhoneNumber())
			.recruitmentStatus(post.getRecruitmentStatus())
			.adminId(post.getAdminId())
			.wageType(post.getWageType())
			.build();
	}
}
