package com.zigtong.clientserver.domain.worker.dto.response;

import java.time.LocalDateTime;

import com.zigtong.clientserver.domain.post.entity.Post;
import com.zigtong.clientserver.domain.relation.entity.WorkerApplicationStatus;
import com.zigtong.clientserver.domain.relation.entity.type.ApplicationStatus;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record WorkerApplicationsStatusResponse(
	Long postId,
	LocalDateTime startTime,
	LocalDateTime endTime,
	String title,
	String address,
	ApplicationStatus status
) {
	public static WorkerApplicationsStatusResponse from(WorkerApplicationStatus workerApplicationStatus) {
		Post post = workerApplicationStatus.getPost();

		return WorkerApplicationsStatusResponse.builder()
			.postId(post.getId())
			.startTime(post.getStartTime())
			.endTime(post.getEndTime())
			.title(post.getTitle())
			.address(post.getAddress())
			.status(workerApplicationStatus.getStatus())
			.build();
	}
}
