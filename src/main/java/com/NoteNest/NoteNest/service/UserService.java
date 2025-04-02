package com.NoteNest.NoteNest.service;


import com.NoteNest.NoteNest.configuration.JwtTokenService;
import com.NoteNest.NoteNest.configuration.SecurityConfig;
import com.NoteNest.NoteNest.dto.CreateUserDto;
import com.NoteNest.NoteNest.dto.LoginUserDto;
import com.NoteNest.NoteNest.dto.RecoveryJwtTokenDto;
import com.NoteNest.NoteNest.model.Role;
import com.NoteNest.NoteNest.model.User;
import com.NoteNest.NoteNest.repository.UserRepository;
import com.NoteNest.NoteNest.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;


    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public void createUser(CreateUserDto createUserDto) {
        User newUser = User.builder()
                .email(createUserDto.email())
                .password(securityConfig.passwordEncoder().encode(createUserDto.password()))
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();
        userRepository.save(newUser);
    }

}
