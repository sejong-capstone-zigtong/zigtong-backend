package com.zigtong.clientserver.domain.worker.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.zigtong.clientserver.domain.post.entity.Post;
import com.zigtong.clientserver.domain.relation.entity.WorkerApplicationStatus;
import com.zigtong.clientserver.domain.worker.dto.request.WorkerSignUpRequest;
import com.zigtong.clientserver.domain.worker.type.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Worker {
	@Id
	@UuidGenerator
	@Column(name = "id", columnDefinition = "char(36)")
	private String id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String memberAccount;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private LocalDate birthdate;

	@Column(columnDefinition = "char(11)", nullable = false, unique = true)
	private String phoneNumber;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;

	@OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WorkerApplicationStatus> workerApplicationStatuses;

	@Builder(access = AccessLevel.PRIVATE)
	private Worker(String name, String memberAccount, String password, LocalDate birthdate, String phoneNumber,
		String nickname, Gender gender) {
		this.name = name;
		this.memberAccount = memberAccount;
		this.password = password;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.nickname = nickname;
		this.gender = gender;
	}

	public static Worker create(WorkerSignUpRequest request, String encryptedPassword) {
		Worker worker = Worker.builder()
			.name(request.name())
			.memberAccount(request.memberAccount())
			.password(encryptedPassword)
			.birthdate(request.birthdate())
			.phoneNumber(request.phoneNumber())
			.nickname(request.nickname())
			.gender(request.gender())
			.build();

		return worker;
	}

	public void apply(Post post) {
		WorkerApplicationStatus status = WorkerApplicationStatus.apply(this, post);
		workerApplicationStatuses.add(status);
	}

	public boolean isApplied(Post post) {
		return workerApplicationStatuses.stream()
			.anyMatch(status -> status.isApplied(post));
	}
}
