package com.healthlink.Doctor_service.controller;

import com.healthlink.Doctor_service.entity.ConsultationFile;
import com.healthlink.Doctor_service.entity.Doctor;
import com.healthlink.Doctor_service.entity.Slot;
import com.healthlink.Doctor_service.service.DoctorService;
import com.healthlink.Doctor_service.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/doctors")
@Validated
public class DoctorController {

    private final SlotService slotService;
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(SlotService slotService, DoctorService doctorService) {
        this.slotService = slotService;
        this.doctorService = doctorService;
    }

    // Ajouter un docteur
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor addedDoctor = doctorService.addDoctor(doctor);
        return ResponseEntity.ok(addedDoctor);
    }
    @GetMapping("/")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    // Récupérer un docteur par email
    @GetMapping("/email/{email}")
    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String email) {
        Doctor doctor = doctorService.getDoctorByEmail(email);
        return ResponseEntity.ok(doctor);
    }

    // Récupérer un docteur par ID
    @GetMapping("/{doctorId}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        return ResponseEntity.ok(doctor);
    }


    // Ajouter des créneaux horaires pour un docteur
    @PostMapping("/{doctorId}/slots")
    public ResponseEntity<List<Slot>> addSlots(@PathVariable Long doctorId, @RequestBody List<Slot> slots) {
        List<Slot> addedSlots = slotService.addSlots(doctorId, slots);
        return ResponseEntity.ok(addedSlots);
    }

    // Récupérer les créneaux horaires d'un docteur
    @GetMapping("/{doctorId}/slots")
    public ResponseEntity<List<Slot>> getSlotsForDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(slotService.getSlotsByDoctor(doctorId));
    }
    @PutMapping("/{doctorId}/slots/{slotId}/book")
    public ResponseEntity<Void> updateSlotBookingStatus(
            @PathVariable Long doctorId,
            @PathVariable Long slotId,
            @RequestParam boolean isBooked) {
        slotService.updateSlotBookingStatus(doctorId, slotId, isBooked);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{doctorId}/slots/id")
    public ResponseEntity<Long> getSlotId(
            @PathVariable Long doctorId,
            @RequestParam LocalDateTime startTime) {
        Long slotId = slotService.findSlotId(doctorId, startTime);
        return ResponseEntity.ok(slotId);
    }
    // Vérifier la disponibilité d'un créneau horaire
    @GetMapping("/{doctorId}/slots/availability")
    public ResponseEntity<Boolean> isSlotAvailable(@PathVariable Long doctorId, @RequestParam LocalDateTime startTime) {
        boolean isAvailable = slotService.isSlotAvailable(doctorId, startTime);
        return ResponseEntity.ok(isAvailable);
    }

    // Mettre à jour les informations d'un docteur
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctorInfo(
            @PathVariable Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "speciality", required = false) String speciality,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) @Email String email,
            @RequestParam(value = "password", required = false) @Size(min = 6, message = "Password must be at least 6 characters") String password,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "biography", required = false) String biography,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        Doctor updatedDoctor = doctorService.getDoctorById(id);

        if (updatedDoctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }

        // Mise à jour des informations
        if (name != null) updatedDoctor.setFirstName(name);
        if (lastName != null) updatedDoctor.setLastName(lastName);
        if (speciality != null) updatedDoctor.setSpeciality(speciality);
        if (city != null) updatedDoctor.setCity(city);
        if (phone != null) updatedDoctor.setPhone(phone);
        if (email != null) updatedDoctor.setEmail(email);
       /* if (password != null) {
            String hashedPassword = String.valueOf(doctorService.hashCode());
            updatedDoctor.setPassword(hashedPassword);
        }*/
        if (address != null) updatedDoctor.setAddress(address);
        if (biography != null) updatedDoctor.setBiography(biography);

        doctorService.saveDoctor(updatedDoctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    // Mettre à jour l'état d'un rendez-vous (appel à microservice appointment)
    @PutMapping("/{doctorId}/appointments/{appointmentId}/update-status")
    public ResponseEntity<String> updateAppointmentStatus(
            @PathVariable Long doctorId,
            @PathVariable Long appointmentId,
            @RequestParam @NotBlank String status) {
        String updatedStatus = doctorService.updateAppointmentStatus(doctorId, appointmentId, status);
        return ResponseEntity.ok(updatedStatus);
    }

    // Partager un fichier de consultation avec un patient (appel à microservice patient)
    @PostMapping("/{doctorId}/files/{fileId}/share")
    public ResponseEntity<ConsultationFile> shareConsultationFile(
            @PathVariable Long doctorId,
            @PathVariable Long fileId,
            @RequestParam Long patientId) {
        ConsultationFile sharedFile = doctorService.shareConsultationFile(doctorId, fileId, patientId);
        return ResponseEntity.ok(sharedFile);
    }
}
