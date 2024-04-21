package com.zigtong.clientserver.domain.worker.controller;

import static com.zigtong.clientserver.global.constant.EndpointConstant.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigtong.clientserver.domain.worker.dto.request.WorkerSignUpRequest;
import com.zigtong.clientserver.domain.worker.service.WorkerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX + "/worker")
public class WorkerController {
	private final WorkerService workerService;

	@PostMapping("/sign-up")
	public ResponseEntity<Void> signUp(@RequestBody WorkerSignUpRequest request, HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession();

		workerService.signUp(request, session);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
