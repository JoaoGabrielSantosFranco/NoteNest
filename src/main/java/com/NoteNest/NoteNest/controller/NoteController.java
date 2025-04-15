package com.NoteNest.NoteNest.controller;

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

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<Object> createNote(@RequestBody NoteDto noteDto, @RequestHeader("Authorization") String token) {
        try {
            noteService.createNote(noteDto, token);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create note.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getUserNotes(@RequestHeader("Authorization") String token) {
        try {
            List<NoteDto> notes = noteService.getUserNotes(token);
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<String> updateNote(@RequestBody NoteDto noteDto, @RequestHeader("Authorization") String token) {
        try {
            noteService.updateNote(noteDto, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update note.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteNote(@RequestBody NoteDto noteDto, @RequestHeader("Authorization") String token) {
        try {
            noteService.deleteNote(noteDto, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete note.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}