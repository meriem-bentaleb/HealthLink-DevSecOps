package com.healthlink.patientservice.service;

import com.healthlink.patientservice.dto.MedicalRecordDTO;
import com.healthlink.patientservice.entity.MedicalRecord;
import com.healthlink.patientservice.entity.Patient;
import com.healthlink.patientservice.repository.MedicalRecordRepository;
import com.healthlink.patientservice.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    // Constructor injection for both repositories
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    // Get all medical records for a specific patient by patientId
    public List<MedicalRecordDTO> getMedicalRecords(Long patientId) {
        List<MedicalRecord> records = medicalRecordRepository.findByPatientId(patientId);
        return records.stream()
                .map(record -> new MedicalRecordDTO(
                        record.getId(),
                        record.getRecordDate(),
                        record.getDetails(),
                        record.getDoctorName(),
                        record.getPatient().getId())) // Correct number of arguments (5)
                .collect(Collectors.toList());
    }

    // Create a new medical record and associate it with a patient
    @Transactional
    public MedicalRecordDTO createMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        // Fetch the Patient entity by patientId
        Patient patient = patientRepository.findById(medicalRecordDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Create new MedicalRecord
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setRecordDate(medicalRecordDTO.getRecordDate());
        medicalRecord.setDetails(medicalRecordDTO.getDetails());
        medicalRecord.setDoctorName(medicalRecordDTO.getDoctorName());
        medicalRecord.setPatient(patient);  // Associate the Patient entity

        // Save the MedicalRecord entity
        medicalRecord = medicalRecordRepository.save(medicalRecord);

        // Return the saved MedicalRecord as DTO
        return new MedicalRecordDTO(
                medicalRecord.getId(),
                medicalRecord.getRecordDate(),
                medicalRecord.getDetails(),
                medicalRecord.getDoctorName(),
                medicalRecord.getPatient().getId()  // Include the patientId in the DTO response
        );
    }
}
