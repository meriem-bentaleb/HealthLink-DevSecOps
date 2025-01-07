package com.rdv.appointmnt.services;

import com.rdv.appointmnt.client.DoctorClient;
import com.rdv.appointmnt.models.Appointement;
import com.rdv.appointmnt.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorClient doctorClient;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorClient doctorClient) {
        this.appointmentRepository = appointmentRepository;
        this.doctorClient = doctorClient;
    }

    public Appointement createAppointment(Appointement appointment) {
        boolean isAvailable = doctorClient.isSlotAvailable(appointment.getDoctorId(), appointment.getAppointmentDateTime());

        if (!isAvailable) {
            appointment.setStatus("REJECTED");
        } else {
            appointment.setStatus("CONFIRMED");

            // Appeler le microservice doctor pour mettre à jour le slot
            doctorClient.updateSlotBookingStatus(
                    appointment.getDoctorId(),
                    findSlotId(appointment),
                    true
            );
        }
        if (appointment.getPatientId() == null) {
            throw new IllegalArgumentException("Patient ID cannot be null");
        }
        return appointmentRepository.save(appointment);
    }

    // Une méthode pour trouver l'ID du slot en fonction du doctorId et de l'heure du rendez-vous
    private Long findSlotId(Appointement appointment) {
        return doctorClient.findSlotId(
                appointment.getDoctorId(),
                appointment.getAppointmentDateTime()
        );
    }
    public List<Appointement> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
    public Appointement updateAppointment(Long id, Appointement updatedAppointment) {
        Appointement existingAppointment = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
        existingAppointment.setAppointmentDateTime(updatedAppointment.getAppointmentDateTime());
        return appointmentRepository.save(existingAppointment);
    }

    public void cancelAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
