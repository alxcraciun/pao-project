package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class YouthExchange extends Project {
    private List<Person> groupLeaders = new ArrayList<>();

    public YouthExchange(int id, String title, String description, String euid, ProjectStatus status, LocalDate startDate, LocalDate endDate) {
        super(id, title, description, euid, status, startDate, endDate);
    }
}
