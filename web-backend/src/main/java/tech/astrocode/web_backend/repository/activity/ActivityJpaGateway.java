package tech.astrocode.web_backend.repository.activity;

import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.dao.OptimisticLockingFailureException;
import tech.astrocode.web_backend.domain.activity.Activity;
import tech.astrocode.web_backend.domain.gateway.ActivityGateway;
import tech.astrocode.web_backend.domain.type.ActivityType;
import tech.astrocode.web_backend.repository.activity.exception.PersistenceException;
import tech.astrocode.web_backend.repository.activity.jpa.ActivityJpaEntity;
import tech.astrocode.web_backend.repository.activity.jpa.ActivityJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityJpaGateway implements ActivityGateway {

    private ActivityJpaRepository repository;

    private ActivityJpaGateway(final ActivityJpaRepository aRepository){
        this.repository = aRepository;
    }

    public static ActivityJpaGateway build(final ActivityJpaRepository aRepository){
        return new ActivityJpaGateway(aRepository);
    }

    @Override
    public void create(final Activity anActivity) {
        final var anActivityEntity = ActivityJpaEntity.from(anActivity);

        try {
            this.repository.save(anActivityEntity);
        }
        catch (IllegalArgumentException | OptimisticLockingFailureException e){
            throw new PersistenceException(e.getMessage());
        }

    }

    @Override
    public void delete(final String anId) {
        try {
            this.repository.deleteById(anId);
        }
        catch (IllegalArgumentException e){
            throw new PersistenceException(e.getMessage());
        }

    }

    @Override
    public List<Activity> findAll() {
       final var aList = this.repository.findAll();

       return aList.stream()
               .map(activityEntity -> activityEntity.toModel())
               .collect(Collectors.toList());
    }
}
