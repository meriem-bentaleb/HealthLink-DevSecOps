package com.healthlink.Doctor_service.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class doctorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String doctorPassword;
    private int age;
    private String specialization;
    private LocalTime startTimeOfWork;

    private LocalTime endTimeOfWork;
    private String biography;
}
