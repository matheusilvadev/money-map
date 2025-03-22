package tech.astrocode.web_backend.service.activity.dtos;

import java.time.Instant;

public record ListActivitiesOutputDTO(
        String id,
        Instant date,
        String description,
        float value,
        String type,
        Instant createdAt,
        Instant updatedAt) {
}
