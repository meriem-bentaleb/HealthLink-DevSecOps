package com.rdv.appointmnt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", url = "http://localhost:8082/api/patients")
public interface PatientClient {
    @GetMapping("/{id}")
    boolean isPatientValid(@PathVariable Long id);
}
