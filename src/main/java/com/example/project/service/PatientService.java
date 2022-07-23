package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Model.Patient;
import com.example.project.repository.PatientRepository;

@Service
public class PatientService {
	@Autowired
	PatientRepository patientRepo;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public Patient patientRegistration(Patient patient) {
		if (!validate(patient))
			return null;
		patient.setPatient_Id(patient.getPatient_name() + "Id");
		Patient newPatient = patientRepo.saveAndFlush(patient);
		logger.info("{}", patientRepo.findAll());
		return newPatient;
	}

	public boolean validate(Patient patient) {
		String regexUsername = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
		String regexEmail = "^(.+)@(.+)$";
		String regexMobile = "^[6-9]\\d{9}$";
		return patient.getPatient_name().matches(regexUsername) && patient.getPatient_email().matches(regexEmail)
				&& patient.getPatient_mobile().matches(regexMobile);
	}

	public Patient viewPatient(String id) {
		Optional<Patient> patient = patientRepo.findByPatientId(id);
		return patient.orElse(null);

	}

	public List<Patient> listPatients() {
		return patientRepo.findAll();
	}
}
