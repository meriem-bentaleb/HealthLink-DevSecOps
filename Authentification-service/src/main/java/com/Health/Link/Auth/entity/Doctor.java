package com.Health.Link.Auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Doctor extends User {
    private String address;
    private boolean certified;
    private String city;
    private String country;
    private String officePhoneNumber;
    private String specialty;
}
