package com.example.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
	Optional<Patient> findByPatientId(String patient_Id);

}
