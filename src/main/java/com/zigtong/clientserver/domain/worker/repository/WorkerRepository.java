package com.zigtong.clientserver.domain.worker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigtong.clientserver.domain.worker.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, String> {
	boolean existsByMemberAccount(String memberAccount);

	boolean existsByPhoneNumber(String phoneNumber);

	boolean existsByNickname(String nickname);

	Optional<Worker> findByMemberAccount(String memberAccount);
}
