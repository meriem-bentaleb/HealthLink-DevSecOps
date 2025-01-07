package com.Health.Link.Auth.repository;

import com.Health.Link.Auth.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
