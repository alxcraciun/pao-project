package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingCourse extends Project {
    private List<Person> trainers = new ArrayList<>();

    public TrainingCourse(int id, String title, String description, String euid, ProjectStatus status, LocalDate startDate, LocalDate endDate) {
        super(id, title, description, euid, status, startDate, endDate);
    }
}
