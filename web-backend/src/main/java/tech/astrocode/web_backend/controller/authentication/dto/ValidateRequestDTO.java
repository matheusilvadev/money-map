package tech.astrocode.web_backend.controller.authentication.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidateRequestDTO(@NotBlank(message = "Token should not be blank") String token) {
}
