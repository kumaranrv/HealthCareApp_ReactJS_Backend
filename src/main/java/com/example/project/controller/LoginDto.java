package com.example.project.controller;

import com.fasterxml.jackson.annotation.JsonAlias;

public class LoginDto {
	@JsonAlias({ "username", "user_name" })
	private String user_name;
	private String password;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDto(String user_name, String password) {
		super();
		this.user_name = user_name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDto [user_name=" + user_name + ", password=" + password + "]";
	}

	public LoginDto() {
		super();
	}

}
