package com.healthlink.Doctor_service.dto;

import java.util.Date;

// DTO pour représenter un enregistrement médical
public class MedicalRecordDTO {

    private Long id;                // ID de l'enregistrement médical, peut être null lors de la création
    private Date recordDate;        // Date de l'enregistrement médical
    private String details;         // Détails de l'enregistrement (par exemple, description de la consultation)
    private String doctorName;      // Nom du médecin
    private Long patientId;         // ID du patient associé à l'enregistrement

    // Constructeur par défaut
    public MedicalRecordDTO() {}

    // Constructeur avec tous les champs
    public MedicalRecordDTO(Long id, Date recordDate, String details, String doctorName, Long patientId) {
        this.id = id;
        this.recordDate = recordDate;
        this.details = details;
        this.doctorName = doctorName;
        this.patientId = patientId;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "MedicalRecordDTO{" +
                "id=" + id +
                ", recordDate=" + recordDate +
                ", details='" + details + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", patientId=" + patientId +
                '}';
    }
}
