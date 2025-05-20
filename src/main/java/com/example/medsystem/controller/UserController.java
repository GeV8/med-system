package com.example.medsystem.controller;

import com.example.medsystem.model.DTO.UserResponseDto;
import com.example.medsystem.model.User;
import com.example.medsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsersNotLinkedToPersonnel() {
        List<User> users = userService.getUsersNotLinkedToPersonnel();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getProfile(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserInfo(username));
    }

}

