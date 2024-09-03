package repository;

import entity.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository {
    void save(Organization organization);

    Optional<Organization> findById(int id);

    List<Organization> findAll();

    void update(Organization organization);

    void delete(int id);

    int getLastId();
}