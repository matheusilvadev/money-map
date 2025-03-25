package tech.astrocode.web_backend.controller.activity.dtos;

import java.time.Instant;

public record InsertActivityRequestDTO(
        Instant date,
        String description,
        String type,
        float value
        ){
}
