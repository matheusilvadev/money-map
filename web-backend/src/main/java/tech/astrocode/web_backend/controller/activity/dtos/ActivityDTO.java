package tech.astrocode.web_backend.controller.activity.dtos;

import java.time.Instant;

public record ActivityDTO(
        String id,
        Instant date,
        String description,
        String type,
        float value) {
}
