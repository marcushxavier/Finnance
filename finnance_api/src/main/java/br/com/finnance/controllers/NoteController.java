package br.com.finnance.controllers;

import br.com.finnance.models.Note;
import br.com.finnance.models.repositories.NoteRepository;
import br.com.finnance.utils.GetObjectFieldsName;
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
    public ResponseEntity<String> getAll() {
        try {
            List<Note> notesList = noteRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(notesList.toString());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PostMapping("/new-note/{user_id}")
    public ResponseEntity<String> createNewNote(@PathVariable(value = "user_id") UUID userId, @RequestBody Note noteData) {
        try {
            Note newNote = new Note(noteData);
            return ResponseEntity.status(HttpStatus.OK).body(noteRepository.save(newNote).toString());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/edit-note")
    public ResponseEntity<String> editNote(@RequestBody Note newNoteData) {
        try {
            //noinspection OptionalGetWithoutIsPresent
            Note noteToUpdate = noteRepository.findById(newNoteData.getId()).get(); // por que choras, IDE?
            new UpdateClass<Note>().update(noteToUpdate, newNoteData, null);

            return ResponseEntity.status(HttpStatus.OK).body(noteRepository.save(noteToUpdate).toString());

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @DeleteMapping("/delete-note/{note_id}")
    public ResponseEntity<String> deleteNote(@PathVariable(name = "note_id") Note noteToDelete) {
        try {
            noteRepository.deleteById(noteToDelete.getId());
            return ResponseEntity.status(HttpStatus.OK).body("item " + noteToDelete.getId() + " deletado");

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("get-notes-of-owner/{owner_id}")
    public ResponseEntity<String> findByOwnerId(@PathVariable(value = "owner_id") UUID ownerId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteRepository.findByOwnerId(ownerId).toString());

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete-all-notes/{owner_id}")
    public void deleteAllByOwnerId(@PathVariable(value = "owner_id") UUID ownerId) {
        try {
            noteRepository.deleteAllByOwnerId(ownerId);
            System.out.println("usuário "+ ownerId + " deletado");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}