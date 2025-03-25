package tech.astrocode.web_backend.service.activity.dtos.mapper;

import tech.astrocode.web_backend.domain.activity.Activity;
import tech.astrocode.web_backend.domain.type.ActivityType;
import tech.astrocode.web_backend.service.activity.dtos.InsertActivityInputDTO;
import tech.astrocode.web_backend.service.exception.ServiceException;


import java.util.function.Function;

public class InsertActivityInputToActivityMapper implements Function<InsertActivityInputDTO, Activity> {

    public static InsertActivityInputToActivityMapper build(){
        return new InsertActivityInputToActivityMapper();
    }

    @Override
    public Activity apply(final InsertActivityInputDTO input) {

        if (input.type().trim().toUpperCase().equals(ActivityType.REVENUE.toString())){

            return Activity.newActivity(
                    input.date(),
                    input.description(),
                    input.value(),
                    ActivityType.REVENUE);
        }
        else if (input.type().trim().toUpperCase().equals(ActivityType.EXPENSE.toString())){

            return Activity.newActivity(
                    input.date(),
                    input.description(),
                    input.value(),
                    ActivityType.EXPENSE);
        }
        else {
            throw new ServiceException("Invalid TYPE");
        }

    }
}
