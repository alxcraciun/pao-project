package service;

import entity.*;
import java.util.*;

public class ApplicationService {
    private static ApplicationService instance;

    private List<Organization> organizations;
    private List<Project> projects;
    private List<Person> persons;
    private List<Application> applications;

    private ApplicationService() {
        organizations = new ArrayList<>();
        projects = new ArrayList<>();
        persons = new ArrayList<>();
        applications = new ArrayList<>();
    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }

    public void addOrganization(Organization org) {
        organizations.add(org);
    }

    public void updateOrganization(Organization org) {
        int index = organizations.indexOf(org);
        if (index != -1) {
            organizations.set(index, org);
        }
    }

    public void deleteOrganization(Organization org) {
        organizations.remove(org);
    }

    public Organization getOrganizationDetails(int id) {
        return organizations.stream()
                .filter(org -> org.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void updateProject(Project project) {
        int index = projects.indexOf(project);
        if (index != -1) {
            projects.set(index, project);
        }
    }

    public void deleteProject(Project project) {
        projects.remove(project);
    }

    public Project getProjectDetails(int id) {
        return projects.stream()
                .filter(proj -> proj.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void updatePerson(Person person) {
        int index = persons.indexOf(person);
        if (index != -1) {
            persons.set(index, person);
        }
    }

    public void deletePerson(Person person) {
        persons.remove(person);
    }

    public Person getPersonDetails(int id) {
        return persons.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addApplication(Application application) {
        applications.add(application);
    }

    public void updateApplication(Application application) {
        int index = applications.indexOf(application);
        if (index != -1) {
            applications.set(index, application);
        }
    }

    public void deleteApplication(Application application) {
        applications.remove(application);
    }

    public Application getApplicationDetails(int id) {
        return applications.stream()
                .filter(app -> app.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Project> listAllProjects() {
        return new ArrayList<>(projects);
    }

    public List<Person> listAllParticipants() {
        return new ArrayList<>(persons);
    }

    public List<Organization> listAllOrganizations() {
        return new ArrayList<>(organizations);
    }

    public void approveApplication(Application application) {
        application.setStatus(ApplicationStatus.APPROVED);
        updateApplication(application);
    }

    public void rejectApplication(Application application) {
        application.setStatus(ApplicationStatus.REJECTED);
        updateApplication(application);
    }

    public List<Application> listApplicationsForProject(Project project) {
        return applications.stream()
                .filter(app -> app.getProject().equals(project))
                .toList();
    }

    public List<Application> listApplicationsForPerson(Person person) {
        return applications.stream()
                .filter(app -> app.getPerson().equals(person))
                .toList();
    }

    public List<Project> listProjectsByCountry(Country country) {
        return projects.stream()
                .filter(proj -> proj.getOrganizer().getCountry().equals(country))
                .toList();
    }
}