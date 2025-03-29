package com.NoteNest.NoteNest.controller;

import com.NoteNest.NoteNest.dto.LoginUserDto;
import com.NoteNest.NoteNest.dto.RecoveryJwtTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserController userController;

    @PostMapping
    public ResponseEntity<RecoveryJwtTokenDto> autheticateUser(@RequestBody LoginUserDto loginUserDto) {
        // TODO: Create service
        return null;
    }
}
