package com.rdv.appointmnt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(name = "doctor-service", url = "http://localhost:8081/api/doctors")
public interface DoctorClient {
    @GetMapping("/{doctorId}/slots/availability")
    boolean isSlotAvailable(@PathVariable Long doctorId, @RequestParam LocalDateTime startTime);

    @PutMapping("/{doctorId}/slots/{slotId}/book")
    void updateSlotBookingStatus(@PathVariable Long doctorId, @PathVariable Long slotId, @RequestParam boolean isBooked);

    @GetMapping("/{doctorId}/slots/id")
    Long findSlotId(@PathVariable Long doctorId, @RequestParam LocalDateTime startTime);
}