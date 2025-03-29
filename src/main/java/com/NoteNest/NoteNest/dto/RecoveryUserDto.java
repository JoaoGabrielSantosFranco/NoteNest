package com.NoteNest.NoteNest.dto;

import com.NoteNest.NoteNest.model.Role;

import java.util.List;

public record RecoveryUserDto(Long id, String email, List<Role> roles) {
}
