package com.example.medsystem.service;

import com.example.medsystem.model.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.function.Function;

public interface JwtService {
    boolean isValid(String token, UserDetails user);


    boolean isValidRefresh(String token, User user);


    String extractUsername(String token);


    <T> T extractClaim(String token, Function<Claims, T> resolver);


    String generateAccessToken(User user);


    String generateRefreshToken(User user);


}
