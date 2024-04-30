package com.zigtong.clientserver.domain.resume.entity;

import java.util.List;

import com.zigtong.clientserver.domain.certificate.entity.Certificate;
import com.zigtong.clientserver.domain.worker.entity.Worker;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
	@Id
	private String id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	private Worker worker;

	private String uploadedUrl;

	private String content;

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ResumeCertificateRelation> resumeCertificateRelations;

	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Career> careers;

	private Resume(Worker worker) {
		this.worker = worker;
	}

	public static Resume create(Worker worker) {
		return new Resume(worker);
	}

	public void updateCertificates(List<Certificate> certificates) {
		resumeCertificateRelations.clear();
		for (Certificate certificate : certificates) {
			resumeCertificateRelations.add(ResumeCertificateRelation.create(this, certificate));
		}
	}

	public void updateStatement(String statement) {
		this.content = statement;
	}

	public void updateCareers(List<Career> careers) {
		this.careers.clear();
		this.careers.addAll(careers);
	}
}
