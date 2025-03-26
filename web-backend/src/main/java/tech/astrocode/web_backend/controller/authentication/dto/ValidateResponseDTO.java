package tech.astrocode.web_backend.controller.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ValidateResponseDTO(@JsonProperty("is_valid") boolean isValid) {
}
