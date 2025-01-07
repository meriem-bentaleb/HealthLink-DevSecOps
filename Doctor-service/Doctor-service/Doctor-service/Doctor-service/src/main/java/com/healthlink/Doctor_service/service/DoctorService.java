package com.healthlink.Doctor_service.service;

import com.healthlink.Doctor_service.entity.ConsultationFile;
import com.healthlink.Doctor_service.entity.Doctor;
import com.healthlink.Doctor_service.repository.ConsultationFileRepository;
import com.healthlink.Doctor_service.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ConsultationFileRepository consultationFileRepository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository,
                         ConsultationFileRepository consultationFileRepository,
                         WebClient.Builder webClientBuilder) {
        this.doctorRepository = doctorRepository;
        this.consultationFileRepository = consultationFileRepository;
        this.webClientBuilder = webClientBuilder;
    }

    // Ajouter un médecin
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Obtenir tous les médecins
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Obtenir un médecin par son ID
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // Mettre à jour les informations d'un médecin, y compris l'image
    public Doctor updateDoctorInfo(Long doctorId, Doctor doctorDetails, String imagePath) throws IOException {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (doctorDetails.getFirstName() != null) doctor.setFirstName(doctorDetails.getFirstName());
        if (doctorDetails.getLastName() != null) doctor.setLastName(doctorDetails.getLastName());
        if (doctorDetails.getSpeciality() != null) doctor.setSpeciality(doctorDetails.getSpeciality());
        if (doctorDetails.getCity() != null) doctor.setCity(doctorDetails.getCity());
        if (doctorDetails.getPhone() != null) doctor.setPhone(doctorDetails.getPhone());
        if (doctorDetails.getEmail() != null) doctor.setEmail(doctorDetails.getEmail());
        if (doctorDetails.getAddress() != null) doctor.setAddress(doctorDetails.getAddress());
        if (doctorDetails.getBiography() != null) doctor.setBiography(doctorDetails.getBiography());
        if (imagePath != null) doctor.setImage(imagePath);

        return doctorRepository.save(doctor);
    }

    // Appeler le microservice de rendez-vous pour accepter ou refuser un rendez-vous
    public String updateAppointmentStatus(Long doctorId, Long appointmentId, String status) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));

        String appointmentServiceUrl = "http://appointment-service/appointments/" + appointmentId + "/status";
        return webClientBuilder.build()
                .post()
                .uri(appointmentServiceUrl)
                .bodyValue(status)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // Partager un fichier de consultation avec un patient via le microservice patient
    public ConsultationFile shareConsultationFile(Long doctorId, Long fileId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
        ConsultationFile file = consultationFileRepository.findById(fileId).orElseThrow(() -> new RuntimeException("File not found"));

        if (!file.getDoctor().equals(doctor)) {
            throw new RuntimeException("You do not have permission to share this file");
        }

        // Vérifier si le patient existe via le microservice patient
        String patientServiceUrl = "http://patient-service/patients/" + patientId;
        Boolean patientExists = webClientBuilder.build()
                .get()
                .uri(patientServiceUrl)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.FALSE.equals(patientExists)) {
            throw new RuntimeException("Patient not found");
        }

        file.setPatientId(patientId);
        return consultationFileRepository.save(file);
    }

    // Sauvegarder un médecin après modification
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Ajouter un fichier de consultation pour un médecin
    public ConsultationFile addConsultationFile(Long doctorId, ConsultationFile consultationFile) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));

        consultationFile.setDoctor(doctor);
        return consultationFileRepository.save(consultationFile);
    }

    public Doctor getDoctorByEmail(String email) {
        System.out.println("Recherche du docteur avec l'email : " + email);
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor == null) {
            System.out.println("Docteur non trouvé pour l'email : " + email);
            throw new EntityNotFoundException("Doctor with email " + email + " not found");
        }
        return doctor;
    }

}
