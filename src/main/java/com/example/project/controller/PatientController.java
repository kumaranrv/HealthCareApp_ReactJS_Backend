package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Patient;
import com.example.project.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	PatientService patientService;

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody Patient patient) {
		Patient newPatient = patientService.patientRegistration(patient);
		if (newPatient != null)
			return new ResponseEntity<>(newPatient, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Object> viewPatient(@PathVariable String id) {
		return new ResponseEntity<>(patientService.viewPatient(id), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<Object> listPatients() {
		return new ResponseEntity<>(patientService.listPatients(), HttpStatus.OK);
	}

}
