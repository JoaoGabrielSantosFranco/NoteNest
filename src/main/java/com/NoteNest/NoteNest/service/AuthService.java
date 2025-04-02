package com.NoteNest.NoteNest.service;

import com.NoteNest.NoteNest.configuration.JwtTokenService;
import com.NoteNest.NoteNest.model.User;
import com.NoteNest.NoteNest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(JwtTokenService jwtTokenService, UserRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    public User getUserFromToken(String token) {
        String username = jwtTokenService.getSubjectFromToken(token.replace("Bearer ", ""));

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
