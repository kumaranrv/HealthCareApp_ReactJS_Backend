package com.example.project.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.project.service.UserAuthService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	JwtUtil jwtUtil;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserAuthService userService;
	@Autowired
	AuthenticationManager authmanager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		String token = null;
		if (header != null)
			token = header.substring(7);

		logger.info("Processing new request with token {}", token);

		if (token != null && jwtUtil.validateToken(token)) {
			logger.info("Token Validity Successful");
			String username = jwtUtil.getUsernameFromToken(token);
			logger.info(username);

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
					new ArrayList<>());
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().contains("/signin") || request.getServletPath().contains("5000/register");

	}

}