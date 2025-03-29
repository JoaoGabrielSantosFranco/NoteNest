package com.NoteNest.NoteNest.dto;

import com.NoteNest.NoteNest.security.RoleName;

public record CreateUserDto(String email, String password, RoleName role) {
}