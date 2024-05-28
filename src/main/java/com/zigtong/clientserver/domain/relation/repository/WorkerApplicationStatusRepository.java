package com.zigtong.clientserver.domain.relation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigtong.clientserver.domain.post.entity.Post;
import com.zigtong.clientserver.domain.relation.entity.WorkerApplicationStatus;

public interface WorkerApplicationStatusRepository extends JpaRepository<WorkerApplicationStatus, Long> {
	List<WorkerApplicationStatus> findAllByPost(Post post);
}
