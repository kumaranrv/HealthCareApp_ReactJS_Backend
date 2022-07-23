package com.example.project.controller;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Mobile {
	@JsonAlias("user_mobile")
	private String mobNumber;

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	@Override
	public String toString() {
		return "Mobile [mobNumber=" + mobNumber + "]";
	}

	public Mobile(String mobNumber) {
		super();
		this.mobNumber = mobNumber;
	}

	public Mobile() {
		super();
	}

}
