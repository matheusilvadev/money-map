package tech.astrocode.web_backend.service.activity.dtos;

import java.time.Instant;

public record InsertActivityInputDTO(
        Instant aDate,
        String description,
        float value,
        String type) {
}
