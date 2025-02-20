package br.com.finnance.views.DTOs;

import java.util.UUID;

public record AuthResponseDTO(UUID userId, String token) {
}
