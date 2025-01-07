package com.Health.Link.Auth.controller;

import com.Health.Link.Auth.dto.DoctorDto;
import com.Health.Link.Auth.dto.PatientDto;
import com.Health.Link.Auth.dto.Response;
import com.Health.Link.Auth.dto.UserDto;
import com.Health.Link.Auth.service.interf.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Health.Link.Auth.dto.LoginRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")

@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register/doctor")
    public ResponseEntity<Response> registerDoctor(@RequestBody DoctorDto doctorDto) {
        Response response = userService.registerDoctor(doctorDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/patient")
    public ResponseEntity<Response> registerPatient(@Valid @RequestBody PatientDto patientDto) {
        Response response = userService.registerPatient(patientDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequest) {
        Response response = userService.loginUser(loginRequest);
        return ResponseEntity.ok(response);
    }
}
