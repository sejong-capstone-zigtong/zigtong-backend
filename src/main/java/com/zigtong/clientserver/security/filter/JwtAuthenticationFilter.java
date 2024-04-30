package com.zigtong.clientserver.security.filter;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zigtong.clientserver.error.ErrorCode;
import com.zigtong.clientserver.error.exception.CustomException;
import com.zigtong.clientserver.security.constant.HeaderConstant;
import com.zigtong.clientserver.security.constant.PublicUris;
import com.zigtong.clientserver.security.jwt.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(
		@NotNull HttpServletRequest request,
		@NotNull HttpServletResponse response,
		@NotNull FilterChain filterChain)
		throws ServletException, IOException {
		String accessToken = jwtProvider.resolveToken(request, HeaderConstant.AUTHORIZATION);

		if (accessToken == null) {
			throw new CustomException(ErrorCode.UNAUTHORIZED);
		}

		boolean isAccessTokenValid = jwtProvider.isTokenValid(accessToken);
		if (!isAccessTokenValid) {
			throw new CustomException(ErrorCode.TOKEN_EXPIRED);
		}

		String memberId = jwtProvider.extractMemberId(accessToken);
		SecurityContextHolder.getContext()
			.setAuthentication(jwtProvider.getAuthentication(memberId));

		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return PublicUris.getAllUrisWithEndpointPrefix()
			.stream()
			.anyMatch(uri -> new AntPathRequestMatcher(uri).matches(request));
	}
}