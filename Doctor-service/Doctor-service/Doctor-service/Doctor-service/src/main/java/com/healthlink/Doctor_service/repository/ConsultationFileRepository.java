package com.healthlink.Doctor_service.repository;

import com.healthlink.Doctor_service.entity.ConsultationFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationFileRepository extends JpaRepository<ConsultationFile, Long> {
}
