package com.example.medsystem.service;

import com.example.medsystem.model.DTO.UserResponseDto;
import com.example.medsystem.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> getUsersNotLinkedToPersonnel();

    UserResponseDto getUserInfo(String username);
}