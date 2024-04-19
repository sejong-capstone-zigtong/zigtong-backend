package com.zigtong.clientserver.domain.certificate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zigtong.clientserver.domain.certificate.entity.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
	List<Certificate> findAllByAffiliation(String affiliationName);

	@Query("SELECT DISTINCT c.affiliation FROM Certificate c")
	List<String> findAllAffiliation();
}
