package com.healthlink.Doctor_service.service;


import com.healthlink.Doctor_service.entity.Slot;
import com.healthlink.Doctor_service.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SlotService {
    private final SlotRepository slotRepository;

    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public List<Slot> addSlots(Long doctorId, List<Slot> slots) {
        for (Slot slot : slots) {
            slot.setDoctorId(doctorId); // Utilise le setter pour attribuer l'ID du médecin
        }
        return slotRepository.saveAll(slots);
    }

    public boolean isSlotAvailable(Long doctorId, LocalDateTime startTime) {
        // Rechercher le créneau correspondant au médecin et à l'heure
        Optional<Slot> slotOptional = slotRepository.findByDoctorIdAndStartTime(doctorId, startTime);

        // Si le créneau existe, vérifier si isBooked est false
        return slotOptional.map(slot -> !slot.isBooked()).orElse(false);
    }

    public List<Slot> getSlotsByDoctor(Long doctorId) {
        return slotRepository.findByDoctorId(doctorId);
    }

    public Long findSlotId(Long doctorId, LocalDateTime startTime) {
        Slot slot = slotRepository.findByDoctorIdAndStartTime(doctorId, startTime)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        return slot.getId();
    }
    public void updateSlotBookingStatus(Long doctorId, Long slotId, boolean isBooked) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        if (!slot.getDoctorId().equals(doctorId)) {
            throw new RuntimeException("Doctor ID mismatch");
        }
        slot.setBooked(isBooked);
        slotRepository.save(slot);
    }
    public boolean deleteSlot(Long doctorId, Long slotId) {
        // Rechercher le créneau par son ID et vérifier si le créneau appartient bien au médecin
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        // Vérification que le créneau appartient bien au médecin avec l'ID fourni
        if (!slot.getDoctorId().equals(doctorId)) {
            throw new RuntimeException("Doctor ID mismatch");
        }

        // Si tout est correct, supprimer le créneau
        slotRepository.delete(slot);

        return true;  // Retourne true si la suppression est réussie
    }

}