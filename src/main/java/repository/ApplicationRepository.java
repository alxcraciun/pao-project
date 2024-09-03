package repository;

import entity.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository {
    void save(Application application);

    Optional<Application> findById(int id);

    List<Application> findAll();

    void update(Application application);

    void delete(int id);

    int getLastId();

    List<Application> findByProject(int projectId);

    List<Application> findByPerson(int personId);
}