package com.zigtong.clientserver.domain.skill.dto.response;

import com.zigtong.clientserver.domain.skill.entity.Skill;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record SkillResponse(
	Integer id,
	String category,
	String name
) {
	public static SkillResponse from(Skill skill) {
		return SkillResponse.builder()
			.id(skill.getId())
			.category(skill.getCategory())
			.name(skill.getName())
			.build();
	}
}
