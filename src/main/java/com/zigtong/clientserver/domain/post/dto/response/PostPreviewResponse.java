package com.zigtong.clientserver.domain.post.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.zigtong.clientserver.domain.post.entity.Post;
import com.zigtong.clientserver.domain.post.entity.type.RecruitmentStatus;
import com.zigtong.clientserver.domain.post.entity.type.WageType;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record PostPreviewResponse(
	Long id,
	String title,
	WageType wageType,
	BigDecimal wage,
	RecruitmentStatus recruitmentStatus,
	LocalDateTime startTime,
	LocalDateTime endTime,
	String category,
	String location
) {
	public static PostPreviewResponse from(Post post) {
		return PostPreviewResponse.builder()
			.id(post.getId())
			//.title(post.getTitle())
			.title(post.getTitle())
			.wageType(post.getWageType())
			.wage(post.getWage())
			.recruitmentStatus(post.getRecruitmentStatus())
			.location(post.getAddress())
			.startTime(post.getStartTime())
			.endTime(post.getEndTime())
			.category(post.getCategory())
			.build();
	}
}
