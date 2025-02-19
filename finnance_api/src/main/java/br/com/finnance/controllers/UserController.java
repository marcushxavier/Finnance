package br.com.finnance.controllers;

import br.com.finnance.models.User;
import br.com.finnance.models.repositories.UserRepository;
import br.com.finnance.utils.UpdateClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<String> getAll() {
        try {
            List<User> allUsers = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(allUsers.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit-user")
    public ResponseEntity<String> editNote(@RequestBody User newUsarData) {
        try {
            User userToUpdate = userRepository.findById(newUsarData.getId()).get();
            new UpdateClass<User>().update(userToUpdate, newUsarData, null);

            return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userToUpdate).toString());

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete-user/{user_id}")
    public ResponseEntity<String> deleteNote(@PathVariable(value = "user_id") UUID userId) {
        try {
            new NoteController().deleteAllByOwnerId(userId);
            userRepository.deleteById(userId);
            return ResponseEntity.status(HttpStatus.OK).body("usuário " + userId + " deletado");

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

