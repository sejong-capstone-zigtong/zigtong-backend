package com.zigtong.clientserver.domain.skill.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zigtong.clientserver.domain.skill.dto.response.SkillCategoryResponse;
import com.zigtong.clientserver.domain.skill.dto.response.SkillResponse;
import com.zigtong.clientserver.domain.skill.service.SkillService;
import com.zigtong.clientserver.global.constant.EndpointConstant;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstant.ENDPOINT_PREFIX + "/skill")
public class SkillController {
	private final SkillService skillService;

	@Operation(summary = "스킬 카테고리 조회", description = "스킬 카테고리를 조회합니다.")
	@GetMapping("/category")
	public List<SkillCategoryResponse> getSkillCategories() {
		return skillService.getSkillCategories();
	}

	@Operation(summary = "카테고리의 스킬 조회", description = "선택한 카테고리의 스킬을 조회합니다. 카테고리 미지정시 모든 스킬을 조회합니다.")
	@GetMapping
	public List<SkillResponse> getSkillsByCategory(@RequestParam(required = false) String category) {
		return skillService.getSkillsByCategory(category);
	}
}
