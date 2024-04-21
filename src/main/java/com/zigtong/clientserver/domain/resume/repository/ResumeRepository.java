package com.zigtong.clientserver.domain.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigtong.clientserver.domain.resume.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
