package br.com.finnance.models.DTOs;

import br.com.finnance.security.roles.UserRole;

public record SignUpDTO(String name, String email, String password, UserRole role) {
}
