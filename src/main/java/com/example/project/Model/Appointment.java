package com.example.project.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Appointment {
	@Id
	@JsonProperty("booking_id")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String bookingId;
	private String disease;
	private Date tentativeDate;
	private String priority;
	private String patientId;
	private String patientName;
	private String description;

	public String getBookingId() {
		return bookingId;
	}

	public void setBooking_id(String booking_id) {
		bookingId = booking_id;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Date getTentativeDate() {
		return tentativeDate;
	}

	public void setTentativeDate(Date tentativeDate) {
		this.tentativeDate = tentativeDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Appointment(String booking_id, String disease, Date tentativeDate, String priority, String patientId,
			String description, String patientName) {
		super();
		bookingId = booking_id;
		this.disease = disease;
		this.tentativeDate = tentativeDate;
		this.priority = priority;
		this.patientId = patientId;
		this.patientName = patientName;
		this.description = description;
	}

	public Appointment() {
		super();
	}

}
