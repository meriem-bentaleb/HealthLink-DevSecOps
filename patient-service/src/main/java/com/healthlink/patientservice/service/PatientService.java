// /src/main/java/com/healthlink/patientservice/service/PatientService.java
package com.healthlink.patientservice.service;

import com.healthlink.patientservice.dto.PatientDTO;
import com.healthlink.patientservice.entity.Patient;
import com.healthlink.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDTO getPatientInfo(Long id) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            return new PatientDTO(
                    patient.getId(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getEmail(),
                    patient.getPhone(),
                    patient.getAddress(),
                    patient.getCity(),
                    patient.getCountry()
            );
        }
        return null;
    }

    // New method to update patient info
    public PatientDTO updatePatientInfo(Long id, PatientDTO patientDTO) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();

            // Update patient fields from the PatientDTO
            patient.setFirstName(patientDTO.getFirstName());
            patient.setLastName(patientDTO.getLastName());
            patient.setEmail(patientDTO.getEmail());
            patient.setPhone(patientDTO.getPhone());
            patient.setAddress(patientDTO.getAddress());
            patient.setCity(patientDTO.getCity());
            patient.setCountry(patientDTO.getCountry());

            // Save the updated patient entity
            patientRepository.save(patient);

            // Return the updated DTO
            return new PatientDTO(
                    patient.getId(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getEmail(),
                    patient.getPhone(),
                    patient.getAddress(),
                    patient.getCity(),
                    patient.getCountry()
            );
        }
        return null; // In case the patient was not found
    }
}
