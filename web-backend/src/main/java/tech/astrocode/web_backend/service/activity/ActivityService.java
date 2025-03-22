package tech.astrocode.web_backend.service.activity;

import tech.astrocode.web_backend.service.activity.dtos.InsertActivityInputDTO;
import tech.astrocode.web_backend.service.activity.dtos.InsertActivityOutputDTO;
import tech.astrocode.web_backend.service.activity.dtos.ListActivitiesOutputDTO;

import java.util.List;

public interface ActivityService {

    public InsertActivityOutputDTO insertActivity(final InsertActivityInputDTO anInput);
    public void removeActivity(final String anId);
    public List<ListActivitiesOutputDTO> listActivities();
    public void calculateBalance();

}
