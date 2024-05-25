package com.zigtong.clientserver.domain.post.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zigtong.clientserver.domain.post.dto.response.PostPreviewResponse;
import com.zigtong.clientserver.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
	private final PostRepository postRepository;

	@Transactional(readOnly = true)
	public List<PostPreviewResponse> getPostPreviews(int page, int size, String category) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

		postRepository.findAllByCategory(category, pageable);

		return postRepository.findAll(pageable)
			.stream()
			.map(PostPreviewResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}
}
