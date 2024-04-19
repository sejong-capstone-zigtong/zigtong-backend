package com.zigtong.clientserver.domain.certificate.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.zigtong.clientserver.config.TestDatasourceConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(classes = TestDatasourceConfiguration.class)
@DataJpaTest
class CertificateRepositoryTest {
	@Autowired
	private CertificateRepository certificateRepository;

	@BeforeEach
	void setUp() {
	}

	@Test
	void 자격증_카테고리_조회() {
	}

	@Test
	void 자격증_카테고리_기준_목록_조회() {
	}
}