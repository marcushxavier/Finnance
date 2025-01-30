package br.com.finnance.controllers;

import br.com.finnance.models.Note;
import br.com.finnance.models.repositories.NoteRepository;
import br.com.finnance.utils.UpdateClass;
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
    public ResponseEntity createNewNote(@PathVariable(value = "user_id") UUID userId, @RequestBody Note noteData) {
        try {
            Note newNote = new Note(userId, noteData.getTitle(), noteData.getValue(), noteData.getFlow(), noteData.getCategory(), noteData.getDate());
            return ResponseEntity.status(HttpStatus.OK).body(noteRepository.save(newNote));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit-note")
    public ResponseEntity editNote(@RequestBody Note newNoteData) {
        try {
            Note noteToUpdate = noteRepository.findById(newNoteData.getId()).get();
            new UpdateClass<Note>().update(noteToUpdate, newNoteData);
            return ResponseEntity.status(HttpStatus.OK).body(noteRepository.save(noteToUpdate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-note")
    public ResponseEntity deleteNote(@RequestBody Note noteToDelete){
        try {
            noteRepository.deleteById(noteToDelete.getId());
            return ResponseEntity.status(HttpStatus.OK).body("item " + noteToDelete.getId() + " deletado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

