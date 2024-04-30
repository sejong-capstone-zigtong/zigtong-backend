package com.zigtong.clientserver.domain.resume.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigtong.clientserver.domain.resume.entity.ResumeCertificateRelation;
import com.zigtong.clientserver.domain.resume.entity.ResumeCertificateRelation.ResumeCertificateRelationId;

public interface ResumeCertificateRelationRepository extends
	JpaRepository<ResumeCertificateRelation, ResumeCertificateRelationId> {
}
