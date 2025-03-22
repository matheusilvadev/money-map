package tech.astrocode.web_backend.service.activity.implementation;

import tech.astrocode.web_backend.domain.gateway.ActivityGateway;
import tech.astrocode.web_backend.domain.type.ActivityType;
import tech.astrocode.web_backend.service.activity.ActivityService;
import tech.astrocode.web_backend.service.activity.dtos.InsertActivityInputDTO;
import tech.astrocode.web_backend.service.activity.dtos.InsertActivityOutputDTO;
import tech.astrocode.web_backend.service.activity.dtos.ListActivitiesOutputDTO;
import tech.astrocode.web_backend.service.activity.dtos.mapper.ActivityToInsertActivityOutputMapper;
import tech.astrocode.web_backend.service.activity.dtos.mapper.ActivityToListActivitiesOutputMapper;
import tech.astrocode.web_backend.service.activity.dtos.mapper.InsertActivityInputToActivityMapper;
import tech.astrocode.web_backend.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityServiceIMP implements ActivityService {

    private ActivityGateway gateway;

    private ActivityServiceIMP(final ActivityGateway aGateway){
        this.gateway = aGateway;
    }

    public static ActivityServiceIMP build(final ActivityGateway aGateway){
        return new ActivityServiceIMP(aGateway);
    }

    @Override
    public InsertActivityOutputDTO insertActivity(final InsertActivityInputDTO anInput) {
        final var anActivity = InsertActivityInputToActivityMapper.build().apply(anInput);

        this.gateway.create(anActivity);

        return ActivityToInsertActivityOutputMapper.build().apply(anActivity);
    }

    @Override
    public void removeActivity(String anId) {
        this.gateway.delete(anId);
    }

    @Override
    public List<ListActivitiesOutputDTO> listActivities() {
        final var aList = this.gateway.findAll();

        return aList.stream()
                .map(a -> ActivityToListActivitiesOutputMapper.build()
                        .apply(a))
                .collect(Collectors.toList());
    }

    @Override
    public float calculateBalance() {
        final var aList = this.gateway.findAll();

        if (aList == null || aList.size() == 0){
            return 0;
        }

        return (float) aList.stream()
                .mapToDouble(a -> a.getType() == ActivityType.REVENUE
                        ? a.getValue()
                        : -a.getValue())
                .sum();
    }
}
