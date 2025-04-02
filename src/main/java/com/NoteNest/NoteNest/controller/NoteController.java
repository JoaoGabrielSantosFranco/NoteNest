package com.NoteNest.NoteNest.controller;

import com.NoteNest.NoteNest.configuration.JwtTokenService;
import com.NoteNest.NoteNest.dto.NoteDto;
import com.NoteNest.NoteNest.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final JwtTokenService jwtTokenService;

    public NoteController(NoteService noteService, JwtTokenService jwtTokenService) {
        this.noteService = noteService;
        this.jwtTokenService = jwtTokenService;
    }


    @PostMapping
    public ResponseEntity<Void> createNote(@RequestBody NoteDto noteDto, @RequestHeader("Authorization") String token) {

        noteService.createNote(noteDto, token);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getUserNotes(@RequestHeader("Authorization") String token) {
        List<NoteDto> notes = noteService.getUserNotes(token);
        return ResponseEntity.ok(notes);
    }

}