package br.com.finnance.controllers;

import br.com.finnance.models.User;
import br.com.finnance.models.repositories.UserRepository;
import br.com.finnance.security.services.TokenService;
import br.com.finnance.views.DTOs.LoginDTO;
import br.com.finnance.views.DTOs.LoginResponseDTO;
import br.com.finnance.views.DTOs.SignUpDTO;
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
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginData) {
        var userPassword = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());
        Authentication auth = this.authenticationManager.authenticate(userPassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody @Valid SignUpDTO userData) throws Exception {
        try {
            if (userRepository.existsByEmail(userData.email())) {
                return ResponseEntity.badRequest().body("email já cadastrado");
            }

            String encriptedPassword = new BCryptPasswordEncoder().encode(userData.password());

            User newUser = new User(userData.name(), userData.email(), encriptedPassword);
            return ResponseEntity.ok(userRepository.save(newUser).toString());

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
