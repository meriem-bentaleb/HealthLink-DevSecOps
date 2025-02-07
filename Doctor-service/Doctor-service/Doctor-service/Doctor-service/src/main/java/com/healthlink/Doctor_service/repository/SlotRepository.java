package com.healthlink.Doctor_service.repository;

import com.healthlink.Doctor_service.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    Optional<Slot> findByDoctorIdAndStartTime(Long doctorId, LocalDateTime startTime);

    List<Slot> findByDoctorId(Long doctorId);
}
