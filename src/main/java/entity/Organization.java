package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private int id;
    private String name;
    private Country country;
    private ContactInfo contact;
    private List<Person> peopleList = new ArrayList<>();

    public Organization(int id, String name, Country country, ContactInfo contact) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public ContactInfo getContact() {
        return contact;
    }

    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getCountry(), that.getCountry());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getCountry());
        return result;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nNume: %s\nȚară: %s\nTelefon: %s\nEmail: %s",
                getId(),
                getName(),
                getCountry(),
                getContact().getPhone(),
                getContact().getEmail());
    }
}
