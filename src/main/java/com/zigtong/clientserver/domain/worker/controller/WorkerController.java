package com.zigtong.clientserver.domain.worker.controller;

import static com.zigtong.clientserver.global.constant.EndpointConstant.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zigtong.clientserver.domain.relation.entity.type.ApplicationStatus;
import com.zigtong.clientserver.domain.worker.dto.request.WorkerSignUpRequest;
import com.zigtong.clientserver.domain.worker.dto.response.WorkerApplicationsStatusResponse;
import com.zigtong.clientserver.domain.worker.service.WorkerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX + "/worker")
public class WorkerController {
	private final WorkerService workerService;

	@Operation(summary = "회원가입", description = "일반 사용자 회원가입을 진행합니다.")
	@PostMapping("/sign-up")
	public ResponseEntity<Void> signUp(@RequestBody WorkerSignUpRequest request, HttpServletRequest servletRequest) {
		//HttpSession session = servletRequest.getSession();

		workerService.signUp(request);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// 지원 상태 조회
	@Operation(summary = "지원 상태 조회", description = "일반 사용자가 지원한 상태를 조회합니다.")
	@Parameter(name = "statuses", description = "PENDING(진행 중), EXPIRED(만료됨), CANCEL(취소함), ACCEPTED(수락됨), REJECTED(거절됨)", required = true)
	@GetMapping("/application/status")
	public List<WorkerApplicationsStatusResponse> findApplicationsStatuses(
		@RequestParam List<ApplicationStatus> statuses) {
		return workerService.findApplicationStatuses(statuses);
	}
}
