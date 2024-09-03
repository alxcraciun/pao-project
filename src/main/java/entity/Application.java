package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Application {
    private int id;
    private Person person;
    private Project project;
    private ApplicationStatus status;
    private LocalDateTime submissionDate;
    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
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

        Application that = (Application) o;
        return getId() == that.getId() && Objects.equals(getPerson(), that.getPerson()) && Objects.equals(getProject(), that.getProject()) && getStatus() == that.getStatus() && Objects.equals(getSubmissionDate(), that.getSubmissionDate()) && Objects.equals(getDetails(), that.getDetails());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + Objects.hashCode(getPerson());
        result = 31 * result + Objects.hashCode(getProject());
        result = 31 * result + Objects.hashCode(getStatus());
        result = 31 * result + Objects.hashCode(getSubmissionDate());
        result = 31 * result + Objects.hashCode(getDetails());
        return result;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\n" +
                        "Participant: %s\n" +
                        "Proiect: %s\n" +
                        "Status: %s\n" +
                        "Data aplicării: %s\n" +
                        "Detalii: %s",
                getId(),
                getPerson().getName(),
                getProject().getTitle(),
                getStatus(),
                getSubmissionDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                getDetails());
    }
}
