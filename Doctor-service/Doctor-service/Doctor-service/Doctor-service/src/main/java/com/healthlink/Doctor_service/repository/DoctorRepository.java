package com.healthlink.Doctor_service.repository;

import com.healthlink.Doctor_service.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByEmail(String email);  // Méthode pour récupérer un docteur par email
}

