package com.Health.Link.Auth.mapper;

import com.Health.Link.Auth.dto.*;
import com.Health.Link.Auth.entity.*;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {

    // Mapping d'une entité User vers un UserDto de base
    public UserDto mapUserToDtoBasic(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());
        userDto.setName(user.getName());
        return userDto;
    }

    // Mapping d'une entité User vers un PatientDto
    public PatientDto mapUserToPatientDto(User user) {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(user.getId());
        patientDto.setName(extractFirstName(user.getName()));
        patientDto.setEmail(user.getEmail());
        patientDto.setRole(user.getRole().name());
        // Ajouter ici d'autres champs spécifiques aux patients, si nécessaire
        return patientDto;
    }

    // Mapping d'une entité User vers un DoctorDto
    public DoctorDto mapUserToDoctorDto(User user) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(user.getId());
        doctorDto.setName(extractFirstName(user.getName()));
        doctorDto.setEmail(user.getEmail());
        doctorDto.setRole(user.getRole().name());
        // Ajouter ici d'autres champs spécifiques aux docteurs, comme la spécialité
        return doctorDto;
    }

    // Méthode utilitaire pour extraire le prénom à partir du champ "name"
    private String extractFirstName(String name) {
        if (name == null || !name.contains(" ")) {
            return name;
        }
        return name.substring(0, name.indexOf(" "));
    }

    // Méthode utilitaire pour extraire le nom de famille à partir du champ "name"
    private String extractLastName(String name) {
        if (name == null || !name.contains(" ")) {
            return "";
        }
        return name.substring(name.indexOf(" ") + 1);
    }
}
