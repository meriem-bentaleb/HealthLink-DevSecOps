package com.healthlink.patientservice.dto;

import java.util.Date;

public class MedicalRecordDTO {

    private Long id;
    private Date recordDate;
    private String details;
    private String doctorName;
    private Long patientId;  // Make sure the patientId is here

    // Constructor for MedicalRecordDTO
    public MedicalRecordDTO(Long id, Date recordDate, String details, String doctorName, Long patientId) {
        this.id = id;
        this.recordDate = recordDate;
        this.details = details;
        this.doctorName = doctorName;
        this.patientId = patientId;
    }

    // Getters and Setters
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
}
