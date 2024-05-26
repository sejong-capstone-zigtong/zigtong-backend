package com.zigtong.clientserver.domain.post.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zigtong.clientserver.domain.post.dto.PostDetailResponse;
import com.zigtong.clientserver.domain.post.dto.response.PostPreviewResponse;
import com.zigtong.clientserver.domain.post.service.PostService;
import com.zigtong.clientserver.global.constant.EndpointConstant;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstant.ENDPOINT_PREFIX + "/post")
public class PostController {
	private final PostService postService;

	@GetMapping
	public List<PostPreviewResponse> getPostPreviews(@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String category) {
		return postService.getPostPreviews(page, size, category);
	}

	@GetMapping("/{id}")
	public PostDetailResponse getPostDetail(@PathVariable Long id) {
		return postService.getPostDetail(id);
	}
}