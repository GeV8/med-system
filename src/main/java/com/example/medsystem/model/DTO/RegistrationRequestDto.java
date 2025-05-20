package com.example.medsystem.model.DTO;

import lombok.Data;

@Data
public class RegistrationRequestDto {

    private String username;
    private String email;
    private String password;

}