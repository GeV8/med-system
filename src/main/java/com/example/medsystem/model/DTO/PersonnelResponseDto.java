package com.example.medsystem.model.DTO;

import com.example.medsystem.model.User;
import lombok.Data;

@Data
public class PersonnelResponseDto {

    private int id;

    private String firstName;

    private String lastName;

    private String specialization;

    private String phone;

    private User user;
}
