package com.NoteNest.NoteNest.service;


import com.NoteNest.NoteNest.dto.NoteDto;
import com.NoteNest.NoteNest.model.Note;
import com.NoteNest.NoteNest.model.User;
import com.NoteNest.NoteNest.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final AuthService authService;

    public NoteService(NoteRepository noteRepository, AuthService authService) {
        this.noteRepository = noteRepository;
        this.authService = authService;
    }

    public void createNote(NoteDto noteDto, String token) {
        User user = authService.getUserFromToken(token);

        Note note = Note.builder()
                .title(noteDto.title())
                .content(noteDto.content())
                .user(user)
                .createAt(LocalDateTime.now())
                .build();

        noteRepository.save(note);
    }

    public List<NoteDto> getUserNotes(String token) {
        User user = authService.getUserFromToken(token);

        return noteRepository.findByUserId(user.getId()).stream()
                .map(note -> new NoteDto(note.getId(), note.getTitle(), note.getContent(), note.getCreateAt()))
                .toList();
    }

    public void updateNote(NoteDto noteDto, String token) {
        Note note = validateAndGetNote(noteDto, token);

        note.setTitle(noteDto.title());
        note.setContent(noteDto.content());

        noteRepository.save(note);
    }

    public void deleteNote(NoteDto noteDto, String token) {
        Note note = validateAndGetNote(noteDto, token);

        noteRepository.deleteById(note.getId());
    }

    private Note validateAndGetNote(NoteDto dto, String token) {
        User user = authService.getUserFromToken(token);

        Note note = noteRepository.findById(dto.id())
                .orElseThrow(() -> new IllegalArgumentException("Note not found: " + dto.id()));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You are not allowed to update this note.");
        }
        return note;
    }
    
}