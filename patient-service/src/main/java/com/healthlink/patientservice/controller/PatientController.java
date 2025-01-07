// /src/main/java/com/healthlink/patientservice/controller/PatientController.java
package com.healthlink.patientservice.controller;

import com.healthlink.patientservice.dto.PatientDTO;
import com.healthlink.patientservice.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientInfo(@PathVariable Long id) {
        return patientService.getPatientInfo(id);
    }
    // PUT request to update patient info
    @PutMapping("/{id}")
    public PatientDTO updatePatientInfo(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        return patientService.updatePatientInfo(id, patientDTO);
    }
}
