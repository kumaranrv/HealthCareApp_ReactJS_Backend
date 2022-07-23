package com.example.project.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
public class Patient {
	@Id
	@JsonAlias("patient_id")
	private String patientId;
	private String patient_name;
	private String patient_email;
	private String patient_mobile;
	private String patient_gender;

	private Date patient_dob;

	public String getPatient_gender() {
		return patient_gender;
	}

	public void setPatient_gender(String patient_gender) {
		this.patient_gender = patient_gender;
	}

	public Date getPatient_dob() {
		return patient_dob;
	}

	public void setPatient_dob(Date patient_dob) {
		this.patient_dob = patient_dob;
	}

	private Date registeredDate;

	public String getPatient_Id() {
		return patientId;
	}

	public void setPatient_Id(String patient_Id) {
		patientId = patient_Id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getPatient_email() {
		return patient_email;
	}

	public void setPatient_email(String patient_email) {
		this.patient_email = patient_email;
	}

	public String getPatient_mobile() {
		return patient_mobile;
	}

	public void setPatient_mobile(String patient_mobile) {
		this.patient_mobile = patient_mobile;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Patient(String patient_name, String patient_email, String patient_mobile, String patient_gender,
			Date patient_dob) {
		super();
		this.patient_name = patient_name;
		this.patient_email = patient_email;
		this.patient_mobile = patient_mobile;
		this.patient_gender = patient_gender;
		this.patient_dob = patient_dob;
	}

	public Patient() {
		super();
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patient_name=" + patient_name + ", patient_email=" + patient_email
				+ ", patient_mobile=" + patient_mobile + ", patient_gender=" + patient_gender + ", patient_dob="
				+ patient_dob + ", registeredDate=" + registeredDate + "]";
	}

}
