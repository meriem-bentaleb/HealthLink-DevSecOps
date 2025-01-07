package com.healthlink.patientservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date recordDate;

    private String details;
    private String doctorName;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;  // Many records can belong to one patient
}
