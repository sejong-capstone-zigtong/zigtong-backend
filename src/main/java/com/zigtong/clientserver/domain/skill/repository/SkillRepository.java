package com.zigtong.clientserver.domain.skill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zigtong.clientserver.domain.skill.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	@Query("SELECT DISTINCT s.category FROM Skill s")
	List<Skill> findAllCategories();

	List<Skill> findByCategory(String category);
}
