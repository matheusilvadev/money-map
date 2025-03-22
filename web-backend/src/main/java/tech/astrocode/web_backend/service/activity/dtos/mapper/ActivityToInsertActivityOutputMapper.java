package tech.astrocode.web_backend.service.activity.dtos.mapper;

import tech.astrocode.web_backend.domain.activity.Activity;
import tech.astrocode.web_backend.service.activity.dtos.InsertActivityOutputDTO;

import java.util.function.Function;

public class ActivityToInsertActivityOutputMapper implements Function<Activity, InsertActivityOutputDTO> {

    public static ActivityToInsertActivityOutputMapper build(){
        return new ActivityToInsertActivityOutputMapper();
    }

    @Override
    public InsertActivityOutputDTO apply(final Activity input) {
        return new InsertActivityOutputDTO(
                input.getId(),
                input.getDate(),
                input.getDescription(),
                input.getValue(),
                input.getType().getValue(),
                input.getCreatedAt(),
                input.getUpdatedAt());
    }
}
