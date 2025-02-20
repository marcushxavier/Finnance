package br.com.finnance.controllers;

import br.com.finnance.models.User;
import br.com.finnance.models.repositories.UserRepository;
import br.com.finnance.security.services.TokenService;
import br.com.finnance.views.DTOs.AuthResponseDTO;
import br.com.finnance.views.DTOs.LoginDTO;
import br.com.finnance.views.DTOs.SignUpDTO;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginData) {
        try {
            var emailAndPassword = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());
            Authentication auth = this.authenticationManager.authenticate(emailAndPassword);
            String token = tokenService.generateToken((User) auth.getPrincipal());

            UUID userId = userRepository.getIdByEmail(loginData.email());

            Gson gson = new Gson();
            AuthResponseDTO response = new AuthResponseDTO(userId, token);

            return ResponseEntity.ok(gson.toJson(response));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody @Valid SignUpDTO userData) throws Exception {
        try {
            if (userRepository.existsByEmail(userData.email())) {
                return ResponseEntity.badRequest().body("email já cadastrado");
            }

            String encriptedPassword = new BCryptPasswordEncoder().encode(userData.password());

            User newUserHolder = new User(userData.name(), userData.email(), encriptedPassword);
            String token = tokenService.generateToken(newUserHolder);

            Gson gson = new Gson();
            AuthResponseDTO response = new AuthResponseDTO(userRepository.save(newUserHolder).getId(), token);

            return ResponseEntity.ok(gson.toJson(response));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
