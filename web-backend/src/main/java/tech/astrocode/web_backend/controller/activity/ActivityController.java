package tech.astrocode.web_backend.controller.activity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.astrocode.web_backend.controller.activity.dtos.CalculateBalanceResponseDTO;
import tech.astrocode.web_backend.controller.activity.dtos.InsertActivityRequestDTO;
import tech.astrocode.web_backend.controller.activity.dtos.InsertActivityResponseDTO;
import tech.astrocode.web_backend.controller.activity.dtos.ListActivitiesResponseDTO;
import tech.astrocode.web_backend.controller.activity.dtos.mapper.InsertActivityOutputServiceToInsertActivityResponseMapper;
import tech.astrocode.web_backend.controller.activity.dtos.mapper.InsertActivityRequestToInsertActivityServiceMapper;
import tech.astrocode.web_backend.controller.activity.dtos.mapper.ListActivitiesToListActivitiesResponseMapper;
import tech.astrocode.web_backend.repository.activity.ActivityJpaGateway;
import tech.astrocode.web_backend.repository.activity.jpa.ActivityJpaRepository;
import tech.astrocode.web_backend.service.activity.dtos.InsertActivityOutputDTO;
import tech.astrocode.web_backend.service.activity.implementation.ActivityServiceIMP;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    ActivityJpaRepository repository;

    @GetMapping
    public ResponseEntity<ListActivitiesResponseDTO> listActivities(){

        final var aGateway = ActivityJpaGateway.build(repository);
        final var aService = ActivityServiceIMP.build(aGateway);
        final var aList = aService.listActivities();

        final var response = ListActivitiesToListActivitiesResponseMapper
                .build()
                .apply(aList);

        return ResponseEntity.ok().body(response);

    }

    @PostMapping
    public ResponseEntity<InsertActivityResponseDTO> insertActivity(@RequestBody InsertActivityRequestDTO input){
        final var aGateway = ActivityJpaGateway.build(repository);
        final var aService = ActivityServiceIMP.build(aGateway);

        final var aServiceInput = InsertActivityRequestToInsertActivityServiceMapper.build()
                .apply(input);

        final var aServiceResponse = aService.insertActivity(aServiceInput);
        final var aResponse = InsertActivityOutputServiceToInsertActivityResponseMapper.build()
                .apply(aServiceResponse);

        return ResponseEntity.ok().body(aResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivityById(@PathVariable("id") final String anId){
        final var aGateway = ActivityJpaGateway.build(repository);
        final var aService = ActivityServiceIMP.build(aGateway);

        aService.removeActivity(anId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<CalculateBalanceResponseDTO> calculateBalance(){
        final var aGateway = ActivityJpaGateway.build(repository);
        final var aService = ActivityServiceIMP.build(aGateway);

        final var aServiceResponse = aService.calculateBalance();
        final var aResponse = new CalculateBalanceResponseDTO(aServiceResponse);

        return ResponseEntity.ok().body(aResponse);
    }

}
