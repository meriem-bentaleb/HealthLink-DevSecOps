// /src/main/java/com/healthlink/patientservice/controller/MedicalRecordController.java
package com.healthlink.patientservice.controller;

import com.healthlink.patientservice.dto.MedicalRecordDTO;
import com.healthlink.patientservice.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin(origins = "http://localhost:3000")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalRecordDTO> getMedicalRecords(@PathVariable Long patientId) {
        return medicalRecordService.getMedicalRecords(patientId);
    }

    // New POST endpoint to create a medical record
    @PostMapping
    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecordDTO createdRecord = medicalRecordService.createMedicalRecord(medicalRecordDTO);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }
}
