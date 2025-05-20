package com.example.medsystem.service.impl;

import com.example.medsystem.model.DTO.PersonnelRequestDto;
import com.example.medsystem.model.DTO.PersonnelResponseDto;
import com.example.medsystem.model.Personnel;
import com.example.medsystem.model.User;
import com.example.medsystem.repository.PersonnelRepository;
import com.example.medsystem.repository.UserRepository;
import com.example.medsystem.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    private final PersonnelRepository personnelRepository;
    private final UserRepository userRepository;

    @Autowired
    public PersonnelServiceImpl(PersonnelRepository personnelRepository, UserRepository userRepository) {
        this.personnelRepository = personnelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PersonnelResponseDto savePersonnel(PersonnelRequestDto dto) {
        Optional<User> userOptional = userRepository.findById(dto.getUserId());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with ID " + dto.getUserId() + " not found");
        }

        User user = userOptional.get();

        Personnel personnel = new Personnel();
        personnel.setFirstName(dto.getFirstName());
        personnel.setLastName(dto.getLastName());
        personnel.setSpecialization(dto.getSpecialization());
        personnel.setPhone(dto.getPhone());
        personnel.setUser(user);

        return toDto(personnelRepository.save(personnel));
    }
    @Override
    public List<PersonnelResponseDto> getAllPersonnel() {
        return personnelRepository.findAll().stream()
                .map(PersonnelServiceImpl::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public PersonnelResponseDto getPersonnelById(int id) {
        return toDto(personnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personnel not found with id " + id)));
    }
    @Override
    public void deletePersonnel(int id) {
        if (!personnelRepository.existsById(id)) {
            throw new RuntimeException("Personnel not found with id " + id);
        }
        personnelRepository.deleteById(id);
    }

    public static PersonnelResponseDto toDto(Personnel personnel) {
        PersonnelResponseDto dto = new PersonnelResponseDto();
        dto.setId(personnel.getId());
        dto.setFirstName(personnel.getFirstName());
        dto.setLastName(personnel.getLastName());
        dto.setSpecialization(personnel.getSpecialization());
        dto.setPhone(personnel.getPhone());
        dto.setUser(personnel.getUser());

        return dto;
    }


}