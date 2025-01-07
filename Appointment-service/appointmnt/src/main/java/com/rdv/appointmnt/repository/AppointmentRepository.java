package com.rdv.appointmnt.repository;


import com.rdv.appointmnt.models.Appointement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointement, Long> {
    List<Appointement> findByDoctorId(Long doctorId);
}

