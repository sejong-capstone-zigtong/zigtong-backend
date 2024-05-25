package com.zigtong.clientserver.domain.skill.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zigtong.clientserver.domain.skill.dto.response.SkillCategoryResponse;
import com.zigtong.clientserver.domain.skill.dto.response.SkillResponse;
import com.zigtong.clientserver.domain.skill.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SkillService {
	private final SkillRepository skillRepository;

	@Transactional(readOnly = true)
	public List<SkillCategoryResponse> getSkillCategories() {
		return skillRepository.findAll()
			.stream()
			.map(SkillCategoryResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}

	public List<SkillResponse> getSkillsByCategory(String category) {
		if (category == null || category.isBlank()) {
			return skillRepository.findAll()
				.stream()
				.map(SkillResponse::from)
				.collect(Collectors.toUnmodifiableList());
		}

		return skillRepository.findByCategory(category)
			.stream()
			.map(SkillResponse::from)
			.collect(Collectors.toUnmodifiableList());
	}
}
