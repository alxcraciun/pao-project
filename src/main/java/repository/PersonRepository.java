package repository;

import entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    void save(Person person);

    Optional<Person> findById(int id);

    List<Person> findAll();

    void update(Person person);

    void delete(int id);

    List<Person> findByCountry(String country);
}