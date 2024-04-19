package com.zigtong.clientserver.domain.certificate.entity;

import com.zigtong.clientserver.domain.certificate.type.CertificateType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Certificate {
	@Id
	@Column(columnDefinition = "SMALLINT UNSIGNED")
	private Integer id;

	@Enumerated(EnumType.STRING)
	private CertificateType type;
	
	private String affiliation;

	private String item;
}
