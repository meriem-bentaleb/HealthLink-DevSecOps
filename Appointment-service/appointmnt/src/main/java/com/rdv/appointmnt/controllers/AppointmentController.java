package com.rdv.appointmnt.controllers;

import com.rdv.appointmnt.models.Appointement;
import com.rdv.appointmnt.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointement>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<Appointement> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appointments);
    }
    @PostMapping
    public ResponseEntity<Appointement> createAppointment(@RequestBody Appointement appointment) {
        Appointement createdAppointment = appointmentService.createAppointment(appointment);
        if (appointment.getPatientId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(createdAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointement> updateAppointment(@PathVariable Long id, @RequestBody Appointement appointment) {
        Appointement updatedAppointment = appointmentService.updateAppointment(id, appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
