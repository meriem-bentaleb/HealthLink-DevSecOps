package com.Health.Link.Auth.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Patient extends User {
    @Transient
    private String confirmPassword; // Non persistant (logique métier)
}
