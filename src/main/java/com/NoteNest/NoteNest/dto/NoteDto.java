package com.NoteNest.NoteNest.dto;

public record NoteDto(Long id, String title, String content, java.time.LocalDateTime createAt) {
}
