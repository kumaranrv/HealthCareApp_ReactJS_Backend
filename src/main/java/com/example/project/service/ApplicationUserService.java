package com.example.project.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.security.JwtUtil;

@Service
public class ApplicationUserService {
	@Autowired
	ApplicationUserRepository userRepo;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	PasswordEncoder passwordEncoder;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public ApplicationUser userRegistration(ApplicationUser user) {
		if (!validate(user))
			return null;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		ApplicationUser newUser = userRepo.saveAndFlush(user);
		logger.info("{}", userRepo.findAll());
		return newUser;
	}

	public boolean validate(ApplicationUser user) {
		String regexUsername = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
		String regexEmail = "^(.+)@(.+)$";
		String regexMobile = "^[6-9]\\d{9}$";
		return user.getUsername().matches(regexUsername) && user.getUser_email().matches(regexEmail)
				&& user.getUser_mobile().matches(regexMobile);
	}

	public Map<String, Object> login(String username, String password) {
		Map<String, Object> res = new HashMap<>();
		logger.info("username: {},Password: {}", username, password);
		UserDetails user = userRepo.findByUsername(username);
		System.out.println("User: " + user);
		System.out.println(res);
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			String token = jwtUtil.generateToken(username);
			res.put("token", token);
			res.put("id", user.getUsername());
			res.put("message", "Authentication Successful");
			return res;
		}
		return res;
	}

	public ApplicationUser getUser(String username) {
		ApplicationUser user = userRepo.findByUsername(username);
		logger.info("User: {}", user);
		return user;
	}

	public ApplicationUser editUser(ApplicationUser appUser, String username) {
		ApplicationUser user = userRepo.findByUsername(username);
		user.setUser_email(appUser.getUser_email());
		user.setLocation(appUser.getLocation());
		user = userRepo.saveAndFlush(user);
		logger.info("User: {}", user);
		return user;
	}

}
