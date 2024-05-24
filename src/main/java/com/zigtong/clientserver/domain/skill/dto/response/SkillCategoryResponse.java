package com.zigtong.clientserver.domain.skill.dto.response;

import com.zigtong.clientserver.domain.skill.entity.Skill;

public record SkillCategoryResponse(
	String category
) {
	public static SkillCategoryResponse from(Skill skill) {
		return new SkillCategoryResponse(skill.getName());
	}
}
