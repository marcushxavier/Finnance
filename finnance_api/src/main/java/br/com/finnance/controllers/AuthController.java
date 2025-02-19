package br.com.finnance.controllers;

import br.com.finnance.models.DTOs.AuthenticationDTO;
import br.com.finnance.models.DTOs.SignUpDTO;
import br.com.finnance.models.User;
import br.com.finnance.models.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO loginData) {
        var userPassword = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());
        var auth = authenticationManager.authenticate(userPassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<String> createUser(@RequestBody @Valid SignUpDTO userData) throws Exception {
        try {
            if (userRepository.existsByEmail(userData.email())) {
                return ResponseEntity.badRequest().body("email já cadastrado");
            }

            String encriptedPassword = new BCryptPasswordEncoder().encode(userData.password());

            User newUser = new User(userData.name(), userData.email(), encriptedPassword, userData.role());
            return ResponseEntity.ok(userRepository.save(newUser).toString());

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
