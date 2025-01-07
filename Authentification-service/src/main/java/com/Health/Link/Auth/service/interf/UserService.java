package com.Health.Link.Auth.service.interf;

import com.Health.Link.Auth.dto.DoctorDto;
import com.Health.Link.Auth.dto.LoginRequest;
import com.Health.Link.Auth.dto.PatientDto;
import com.Health.Link.Auth.dto.Response;
import com.Health.Link.Auth.dto.UserDto;
import com.Health.Link.Auth.entity.User;

public interface UserService {

    // Inscription pour un utilisateur générique (si nécessaire)
    Response registerUser(UserDto registrationRequest);

    // Inscription pour un patient
    Response registerPatient(PatientDto patientDto);

    // Inscription pour un docteur
    Response registerDoctor(DoctorDto doctorDto);

    // Connexion
    Response loginUser(LoginRequest loginRequest);

    // Récupérer tous les utilisateurs
    Response getAllUsers();

    // Récupérer l'utilisateur actuellement connecté
    User getLoginUser();
}
