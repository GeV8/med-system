package com.example.medsystem.controller;

import com.example.medsystem.model.DTO.AuthenticationResponseDto;
import com.example.medsystem.model.DTO.LoginRequestDto;
import com.example.medsystem.model.DTO.RegistrationRequestDto;
import com.example.medsystem.service.AuthenticationService;
import com.example.medsystem.service.impl.AuthenticationServiceImpl;
import com.example.medsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(
            @RequestBody RegistrationRequestDto registrationDto) {

        if(userService.existsByUsername(registrationDto.getUsername())) {
            return ResponseEntity.badRequest().body("Ім'я зайняте");
        }

        if(userService.existsByEmail(registrationDto.getEmail())) {
            return ResponseEntity.badRequest().body("Email зайнят");
        }

        authenticationService.register(registrationDto);

        return ResponseEntity.ok("Регістрація пройшла успішно");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody LoginRequestDto request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {

        return authenticationService.refreshToken(request, response);
    }
}