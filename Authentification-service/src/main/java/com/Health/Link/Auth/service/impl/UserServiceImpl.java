package com.Health.Link.Auth.service.impl;

import com.Health.Link.Auth.dto.*;
import com.Health.Link.Auth.entity.Doctor;
import com.Health.Link.Auth.entity.Patient;
import com.Health.Link.Auth.entity.User;
import com.Health.Link.Auth.enums.UserRole;
import com.Health.Link.Auth.exception.InvalidCredentialsException;
import com.Health.Link.Auth.exception.NotFoundException;
import com.Health.Link.Auth.mapper.EntityDtoMapper;
import com.Health.Link.Auth.repository.DoctorRepository;
import com.Health.Link.Auth.repository.PatientRepository;
import com.Health.Link.Auth.repository.UserRepo;
import com.Health.Link.Auth.security.JwtUtils;
import com.Health.Link.Auth.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EntityDtoMapper entityDtoMapper;

    // Inscription générique pour un utilisateur
    @Override
    public Response registerUser(UserDto registrationRequest) {
        UserRole role = UserRole.USER;

        if (registrationRequest.getRole() != null && registrationRequest.getRole().equalsIgnoreCase("admin")) {
            role = UserRole.ADMIN;
        }

        User user = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phoneNumber(registrationRequest.getPhoneNumber())
                .role(role)
                .build();

        User savedUser = userRepo.save(user);

        UserDto userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);
        return Response.builder()
                .status(200)
                .message("User Successfully Added")
                .user(userDto)
                .build();
    }

    // Inscription d'un patient
    @Override
    public Response registerPatient(PatientDto patientDto) {
        if (!patientDto.getPassword().equals(patientDto.getConfirmPassword())) {
            throw new InvalidCredentialsException("Passwords do not match");
        }

        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setEmail(patientDto.getEmail());
        patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setRole(UserRole.PATIENT);

        Patient savedPatient = patientRepository.save(patient);

        PatientDto savedPatientDto = entityDtoMapper.mapUserToPatientDto(savedPatient);

        return Response.builder()
                .status(200)
                .message("Patient Successfully Registered")
                .user(savedPatientDto)
                .build();
    }

    // Inscription d'un docteur
    @Override
    public Response registerDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPassword(passwordEncoder.encode(doctorDto.getPassword()));
        doctor.setPhoneNumber(doctorDto.getPhoneNumber());
        doctor.setRole(UserRole.DOCTOR);
        doctor.setAddress(doctorDto.getAddress());
        doctor.setCertified(doctorDto.isCertified());
        doctor.setCity(doctorDto.getCity());
        doctor.setCountry(doctorDto.getCountry());
        doctor.setOfficePhoneNumber(doctorDto.getOfficePhoneNumber());
        doctor.setSpecialty(doctorDto.getSpecialty());

        Doctor savedDoctor = doctorRepository.save(doctor);

        DoctorDto savedDoctorDto = entityDtoMapper.mapUserToDoctorDto(savedDoctor);

        return Response.builder()
                .status(200)
                .message("Doctor Successfully Registered")
                .user(savedDoctorDto)
                .build();
    }

    // Connexion de l'utilisateur
    @Override
    public Response loginUser(LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("Email not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Password does not match");
        }

        String token = jwtUtils.generateToken(user);

        return Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token(token)
                .expirationTime("6 Month")
                .role(user.getRole().name())
                .build();
    }

    // Récupérer tous les utilisateurs
    @Override
    public Response getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream()
                .map(entityDtoMapper::mapUserToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .userList(userDtos)
                .build();
    }

    // Récupérer l'utilisateur actuellement connecté
    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        log.info("User Email is: " + email);

        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
    }

    // Mise à jour des informations utilisateur
    //@Override
    public Response updateUser(UserDto userDto) {
        User user = userRepo.findById(userDto.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        User updatedUser = userRepo.save(user);
        UserDto updatedUserDto = entityDtoMapper.mapUserToDtoBasic(updatedUser);

        return Response.builder()
                .status(200)
                .message("User Successfully Updated")
                .user(updatedUserDto)
                .build();
    }
}
