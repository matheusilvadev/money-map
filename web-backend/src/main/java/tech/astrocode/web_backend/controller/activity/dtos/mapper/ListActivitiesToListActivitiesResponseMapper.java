package tech.astrocode.web_backend.controller.activity.dtos.mapper;

import tech.astrocode.web_backend.controller.activity.dtos.ActivityDTO;
import tech.astrocode.web_backend.controller.activity.dtos.ListActivitiesResponseDTO;
import tech.astrocode.web_backend.service.activity.dtos.ListActivitiesOutputDTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ListActivitiesToListActivitiesResponseMapper implements Function<List<ListActivitiesOutputDTO>, ListActivitiesResponseDTO> {

   public static ListActivitiesToListActivitiesResponseMapper build(){
       return new ListActivitiesToListActivitiesResponseMapper();
   }

    //Mapping from ListActivitiesOutputDTO to ListActivitiesResponseDTO
    @Override
    public ListActivitiesResponseDTO apply(final List<ListActivitiesOutputDTO> input) {
        final var aList = input.stream()
                .map(a -> new ActivityDTO(
                        a.id(),
                        a.date(),
                        a.description(),
                        a.type(),
                        a.value()
                ))
                .collect(Collectors.toList());

        return new ListActivitiesResponseDTO(aList);
    }
}
