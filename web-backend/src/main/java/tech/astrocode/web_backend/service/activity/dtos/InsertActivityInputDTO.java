package tech.astrocode.web_backend.service.activity.dtos;

import java.time.Instant;

public record InsertActivityInputDTO(
        Instant date,
        String description,
        float value,
        String type) {
}
