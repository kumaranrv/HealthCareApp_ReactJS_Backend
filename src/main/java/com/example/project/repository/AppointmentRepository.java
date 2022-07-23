package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
	Appointment findByPatientId(String patientId);

}
