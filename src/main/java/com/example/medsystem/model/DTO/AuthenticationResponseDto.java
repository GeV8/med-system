package com.example.medsystem.model.DTO;

import com.example.medsystem.model.Role;
import lombok.Getter;

@Getter
public class AuthenticationResponseDto {

    private final String accessToken;

    private final String refreshToken;

    private final Role role;


    public AuthenticationResponseDto(String token, String refreshToken, Role role) {
        this.accessToken = token;
        this.refreshToken = refreshToken;
        this.role = role;
    }
}