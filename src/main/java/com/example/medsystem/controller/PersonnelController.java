package com.example.medsystem.controller;


import com.example.medsystem.model.DTO.PersonnelRequestDto;
import com.example.medsystem.model.DTO.PersonnelResponseDto;
import com.example.medsystem.service.PersonnelService;
import com.example.medsystem.service.impl.PersonnelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    private final PersonnelService personnelService;

    @Autowired
    public PersonnelController(PersonnelServiceImpl personnelService) {
        this.personnelService = personnelService;
    }

    @PostMapping
    public ResponseEntity<PersonnelResponseDto> createPersonnel(@RequestBody PersonnelRequestDto dto) {
        PersonnelResponseDto saved = personnelService.savePersonnel(dto);
        return ResponseEntity
                .created(URI.create("/api/personnel/" + saved.getId()))
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<PersonnelResponseDto>> getAllPersonnel() {
        List<PersonnelResponseDto> list = personnelService.getAllPersonnel();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonnelResponseDto> getPersonnelById(@PathVariable int id) {
        PersonnelResponseDto personnel = personnelService.getPersonnelById(id);
        return ResponseEntity.ok(personnel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonnelResponseDto> deletePersonnelById(@PathVariable int id) {
            personnelService.deletePersonnel(id);
            return ResponseEntity.ok().build();
    }


}
