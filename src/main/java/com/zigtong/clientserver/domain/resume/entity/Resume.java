package com.zigtong.clientserver.domain.resume.entity;

import java.util.List;

import com.zigtong.clientserver.domain.worker.entity.Worker;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Resume {
	@Id
	private String id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	private Worker worker;

	private String uploadedUrl;

	private String content;

	@OneToMany(mappedBy = "resume")
	private List<ResumeCertificateRelation> resumeCertificateRelations;

	private Resume(Worker worker) {
		this.worker = worker;
	}

	public static Resume create(Worker worker) {
		return new Resume(worker);
	}
}
