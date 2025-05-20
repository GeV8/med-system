package com.example.medsystem.service;

import com.example.medsystem.model.DTO.PersonnelRequestDto;
import com.example.medsystem.model.DTO.PersonnelResponseDto;
import com.example.medsystem.model.Personnel;

import java.util.List;

public interface PersonnelService {
     PersonnelResponseDto savePersonnel(PersonnelRequestDto dto);

     List<PersonnelResponseDto> getAllPersonnel();

     PersonnelResponseDto getPersonnelById(int id);

     void deletePersonnel(int id);

}
