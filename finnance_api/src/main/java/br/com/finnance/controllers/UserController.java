package br.com.finnance.controllers;

import br.com.finnance.models.User;
import br.com.finnance.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity hello() {
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }

    @GetMapping("/user")
    public ResponseEntity getAll() {
        try {
            List<User> allUsers = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(allUsers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("new-user")
    public ResponseEntity createUser(@RequestBody User userData) {
        try {
            User newUser = new User(userData.getName(), userData.getEmail(), userData.getPassword());
//            return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(newUser));
            return ResponseEntity.status(HttpStatus.OK).body("fhafhidahf");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
