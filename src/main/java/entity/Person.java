package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private int id;
    private String name;
    private Country country;
    private LocalDate dateOfBirth;
    private int age;
    private ContactInfo contact;
    private List<Application> applicationList = new ArrayList<>();

    public Person(int id, Country country, String name, LocalDate dateOfBirth, int age, ContactInfo contact) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ContactInfo getContact() {
        return contact;
    }

    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return getId() == person.getId() && Objects.equals(getName(), person.getName()) && Objects.equals(getCountry(), person.getCountry()) && Objects.equals(getDateOfBirth(), person.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getCountry());
        result = 31 * result + Objects.hashCode(getDateOfBirth());
        return result;
    }
}
