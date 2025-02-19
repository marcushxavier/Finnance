package br.com.finnance.controllers;

import br.com.finnance.models.User;
import br.com.finnance.models.repositories.UserRepository;
import br.com.finnance.utils.UpdateClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{user_id}/get-user")
    public ResponseEntity<User> getUser(@PathVariable("user_id") UUID userId) {
        return ResponseEntity.ok(userRepository.getReferenceById(userId));
    }

    @PutMapping("/{user_id}/edit-user")
    public ResponseEntity<String> editUser(@RequestBody User newUserData) {
        try {
            User userToUpdate = userRepository.findById(newUserData.getId()).get();
            new UpdateClass<User>().update(userToUpdate, newUserData, null);

            return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userToUpdate).toString());

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{user_id}/delete-user")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "user_id") UUID userId) {
        try {
            new NoteController().deleteAllByOwnerId(userId);
            userRepository.deleteById(userId);
            return ResponseEntity.status(HttpStatus.OK).body("usuário " + userId + " deletado");

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

