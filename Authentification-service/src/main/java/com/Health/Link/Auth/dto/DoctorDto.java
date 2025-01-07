package com.Health.Link.Auth.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorDto extends UserDto {
    private String address; // Adresse du médecin
    private boolean certified; // Indique si le médecin est certifié
    private String city; // Ville du médecin
    private String country; // Pays du médecin
    private String officePhoneNumber; // Numéro de téléphone du cabinet
    private String specialty; // Spécialité du médecin

    // Rôle par défaut pour les docteurs
    public DoctorDto() {
        super.setRole("DOCTOR");
    }
}
