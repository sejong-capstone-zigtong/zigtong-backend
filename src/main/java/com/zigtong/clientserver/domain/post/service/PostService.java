package com.zigtong.clientserver.domain.post.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zigtong.clientserver.domain.post.dto.PostDetailResponse;
import com.zigtong.clientserver.domain.post.dto.response.PostPreviewResponse;
import com.zigtong.clientserver.domain.post.entity.Post;
import com.zigtong.clientserver.domain.post.repository.PostRepository;
import com.zigtong.clientserver.domain.worker.entity.Worker;
import com.zigtong.clientserver.domain.worker.repository.WorkerRepository;
import com.zigtong.clientserver.error.ErrorCode;
import com.zigtong.clientserver.error.exception.CustomException;
import com.zigtong.clientserver.security.util.SecurityContextUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
	private final PostRepository postRepository;
	private final WorkerRepository workerRepository;

	@Transactional(readOnly = true)
	public List<PostPreviewResponse> getPostPreviews(int page, int size, String category) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

		postRepository.findAllByCategory(category, pageable);

		return postRepository.findAll(pageable)
			.stream()
			.map(PostPreviewResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}

	@Transactional(readOnly = true)
	public PostDetailResponse getPostDetail(Long id) {
		return postRepository.findById(id)
			.map(PostDetailResponse::from)
			.orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
	}

	public void applyPost(Long postId) {
		String workerId = SecurityContextUtil.extractWorkerId();
		Worker worker = workerRepository.findById(workerId)
			.orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

		validateApply(worker, post);

		worker.apply(post);
	}

	private void validateApply(Worker worker, Post post) {
		if (worker.isApplied(post)) {
			throw new CustomException(ErrorCode.ALREADY_APPLIED);
		}
		if (post.isClosed()) {
			throw new CustomException(ErrorCode.POST_NOT_FOUND);
		}
	}
}