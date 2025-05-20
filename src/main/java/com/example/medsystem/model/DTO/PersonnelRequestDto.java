package com.example.medsystem.model.DTO;


import lombok.Data;

@Data
public class PersonnelRequestDto {

    private String firstName;

    private String lastName;

    private String specialization;

    private String phone;

    private int userId;
}
