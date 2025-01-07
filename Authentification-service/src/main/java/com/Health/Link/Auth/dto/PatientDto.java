package com.Health.Link.Auth.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PatientDto extends UserDto {
    private String confirmPassword; // Champ supplémentaire pour confirmer le mot de passe

    // Rôle par défaut pour les patients
    public PatientDto() {
        super.setRole("PATIENT");
    }
}
