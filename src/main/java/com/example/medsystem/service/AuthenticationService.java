package com.example.medsystem.service;

import com.example.medsystem.model.DTO.AuthenticationResponseDto;
import com.example.medsystem.model.DTO.LoginRequestDto;
import com.example.medsystem.model.DTO.RegistrationRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    void register(RegistrationRequestDto request);

    AuthenticationResponseDto authenticate(LoginRequestDto request);

    ResponseEntity<AuthenticationResponseDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response);

}
