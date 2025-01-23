package br.com.finnance.controllers;

import br.com.finnance.models.Note;
import br.com.finnance.models.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
class NoteController {
    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/notes")
    public ResponseEntity getAll() {
        try {

            List<Note> notesList = noteRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(notesList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PostMapping("/{user_id}/new-note")
    public ResponseEntity newNote(@PathVariable(value = "user_id") UUID userId, @RequestBody Note noteData) {
        try {
            Note newNote = new Note(
                    userId,
                    noteData.getTitle(),
                    noteData.getValue(),
                    noteData.isOutflow(),
                    noteData.getCategory(),
                    noteData.getDate());
            return ResponseEntity.status(HttpStatus.OK).body(noteRepository.save(newNote));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}
