package tech.astrocode.web_backend.controller.activity.dtos.mapper;

import tech.astrocode.web_backend.controller.activity.dtos.InsertActivityRequestDTO;
import tech.astrocode.web_backend.service.activity.dtos.InsertActivityInputDTO;

import java.util.function.Function;

public class InsertActivityRequestToInsertActivityServiceMapper implements Function<InsertActivityRequestDTO, InsertActivityInputDTO> {

    public static InsertActivityRequestToInsertActivityServiceMapper build(){
        return new InsertActivityRequestToInsertActivityServiceMapper();
    }

    //
    @Override
    public InsertActivityInputDTO apply(final InsertActivityRequestDTO input) {
        return new InsertActivityInputDTO(
                input.date(),
                input.description(),
                input.value(),
                input.type());
    }
}
