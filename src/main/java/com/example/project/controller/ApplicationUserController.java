package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.ApplicationUser;
import com.example.project.service.ApplicationUserService;

@RestController
public class ApplicationUserController {
	@Autowired
	ApplicationUserService userService;

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody ApplicationUser user) {
		ApplicationUser newUser = userService.userRegistration(user);
		if (newUser != null) {
			Map<String, String> res = new HashMap<>();
			res.put("message", "registration successful");
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/signin")
	public ResponseEntity<Object> signin(@RequestBody LoginDto loginDto) {
		Map<String, Object> res = userService.login(loginDto.getUser_name(), loginDto.getPassword());
		System.out.println(res);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@GetMapping("/viewprofile/{username}")
	public ResponseEntity<Object> viewProfile(@PathVariable String username) {
		return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
	}

	@PutMapping("/editprofile/{username}")
	public ResponseEntity<Object> editProfile(@PathVariable String username, @RequestBody ApplicationUser user) {
		return new ResponseEntity<>(userService.editUser(user, username), HttpStatus.OK);

	}

}
