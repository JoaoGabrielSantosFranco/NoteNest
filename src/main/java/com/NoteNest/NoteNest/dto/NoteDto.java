package com.NoteNest.NoteNest.dto;

public record NoteDto(String title, String content, java.time.LocalDateTime createAt) {
}
