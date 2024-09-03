package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Project {
    protected int id;
    protected String title;
    protected String description;
    protected String euid;
    protected ProjectStatus status;
    protected Organization organizer;
    protected List<Person> participants = new ArrayList<>();
    private List<Application> applicationList = new ArrayList<>();

    protected LocalDate startDate;
    protected LocalDate endDate;

    public Project(int id, String title, String description, String euid, ProjectStatus status, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.euid = euid;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEuid() {
        return euid;
    }

    public void setEuid(String euid) {
        this.euid = euid;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Organization getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organization organizer) {
        this.organizer = organizer;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;
        return Objects.equals(getEuid(), project.getEuid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEuid());
    }

    @Override
    public String toString() {
        return String.format("Detalii proiect:\n" +
                        "ID: %d\n" +
                        "Titlu: %s\n" +
                        "Descriere: %s\n" +
                        "EUID: %s\n" +
                        "Status: %s\n" +
                        "Data de început: %s\n" +
                        "Data de sfârșit: %s",
                getId(),
                getTitle(),
                getDescription(),
                getEuid(),
                getStatus(),
                getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
