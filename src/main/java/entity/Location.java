package entity;

import java.util.Objects;

public class Location {
    private Country country;
    private String city;
    private String details;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(getCountry(), location.getCountry()) && Objects.equals(getCity(), location.getCity()) && Objects.equals(getDetails(), location.getDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCity(), getDetails());
    }

    @Override
    public String toString() {
        return "Address{" +
                "country=" + country +
                ", city='" + city + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}

