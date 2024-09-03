package repository;

import entity.Project;
import entity.Country;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    void save(Project project);

    Optional<Project> findById(int id);

    List<Project> findAll();

    void update(Project project);

    void delete(int id);

    int getLastId();

    List<Project> findByCountry(Country country);
}