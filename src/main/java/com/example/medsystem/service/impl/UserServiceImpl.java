package com.example.medsystem.service.impl;

import com.example.medsystem.model.DTO.UserResponseDto;
import com.example.medsystem.model.User;
import com.example.medsystem.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.medsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserResponseDto getUserInfo(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Користувач " + username + " не знайден"));
        return new UserResponseDto(user.getUsername(), user.getEmail(), user.getRole().toString());
    }

    @Override
    public List<User> getUsersNotLinkedToPersonnel(){
        return userRepository.findUsersNotLinkedToPersonnel();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Користувач " + username + " не знайден"));
    }

    @Override
    public boolean existsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return true;
        }
        return false;
    }
}
