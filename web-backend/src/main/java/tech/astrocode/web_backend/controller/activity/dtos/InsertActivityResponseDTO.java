package tech.astrocode.web_backend.controller.activity.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record InsertActivityResponseDTO(
        String id,
        Instant date,
        String description,
        float value,
        String type,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}
