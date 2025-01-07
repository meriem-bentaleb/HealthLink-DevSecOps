// /src/main/java/com/healthlink/patientservice/repository/MedicalRecordRepository.java
package com.healthlink.patientservice.repository;

import com.healthlink.patientservice.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientId(Long patientId);
}
