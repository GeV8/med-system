package com.example.medsystem.model.DTO;

import lombok.Data;

@Data
public class UserResponseDto {
    private String username;
    private String email;
    private String role;

    public UserResponseDto(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
