package com.Health.Link.Auth.repository;

import com.Health.Link.Auth.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
