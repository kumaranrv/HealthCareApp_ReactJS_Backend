package com.example.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Appointment;
import com.example.project.repository.AppointmentRepository;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	AppointmentRepository apRepo;

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody Appointment apt) {
		if (apRepo.findByPatientId(apt.getPatientId()) != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Appointment newApt = apRepo.save(apt);
		return new ResponseEntity<>(newApt, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<Object> listAllAppointments(@PathVariable(required = false) String id) {
		return new ResponseEntity<>(apRepo.findAll(), HttpStatus.OK);

	}

	@GetMapping("/list/{id}")
	public ResponseEntity<Object> listAppointment(@PathVariable(required = false) String id) {
		Optional<Appointment> apt = apRepo.findById(id);
		return apt.<ResponseEntity<Object>>map(appointment -> new ResponseEntity<>(appointment, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteAppointment(@PathVariable String id) {
		apRepo.deleteById(id);
		if (!apRepo.findById(id).isPresent())
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
