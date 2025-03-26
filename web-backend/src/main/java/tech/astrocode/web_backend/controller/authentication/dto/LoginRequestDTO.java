package tech.astrocode.web_backend.controller.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank(message = "Email should not be blank") @Email String email,
                              @NotBlank(message = "Password should not be blank") String password) {
}
