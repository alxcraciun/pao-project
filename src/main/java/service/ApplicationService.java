package service;

import entity.*;
import repository.*;
import java.util.*;

public class ApplicationService {
    private static ApplicationService instance;

    private OrganizationRepository organizationRepository;
    private ProjectRepository projectRepository;
    private PersonRepository personRepository;
    private ApplicationRepository applicationRepository;
    private AuditService auditService;

    private ApplicationService() {
        organizationRepository = OrganizationRepositoryImpl.getInstance();
        projectRepository = ProjectRepositoryImpl.getInstance();
        personRepository = PersonRepositoryImpl.getInstance();
        applicationRepository = ApplicationRepositoryImpl.getInstance();
        auditService = AuditService.getInstance();
    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }

    public void addOrganization(Organization org) {
        organizationRepository.save(org);
        auditService.logAction("ADD_ORGANIZATION");
    }

    public void updateOrganization(Organization org) {
        organizationRepository.update(org);
        auditService.logAction("UPDATE_ORGANIZATION");
    }

    public void deleteOrganization(Organization org) {
        organizationRepository.delete(org.getId());
        auditService.logAction("DELETE_ORGANIZATION");
    }

    public Organization getOrganizationDetails(int id) {
        auditService.logAction("GET_ORGANIZATION_DETAILS");
        return organizationRepository.findById(id).orElse(null);
    }

    public void addProject(Project project) {
        auditService.logAction("ADD_PROJECT");
        projectRepository.save(project);
    }

    public void updateProject(Project project) {
        auditService.logAction("UPDATE_PROJECT");
        projectRepository.update(project);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project.getId());
        auditService.logAction("DELETE_PROJECT");
    }

    public Project getProjectDetails(int id) {
        auditService.logAction("GET_PROJECT_DETAILS");
        return projectRepository.findById(id).orElse(null);
    }

    public void addPerson(Person person) {
        auditService.logAction("ADD_PERSON");
        personRepository.save(person);
    }

    public void updatePerson(Person person) {
        auditService.logAction("UPDATE_PERSON");
        personRepository.update(person);
    }

    public void deletePerson(Person person) {
        auditService.logAction("DELETE_PERSON");
        personRepository.delete(person.getId());
    }

    public Person getPersonDetails(int id) {
        auditService.logAction("GET_PERSON_DETAILS");
        return personRepository.findById(id).orElse(null);
    }

    public void addApplication(Application application) {
        applicationRepository.save(application);
        auditService.logAction("ADD_APPLICATION");
    }

    public void updateApplication(Application application) {
        applicationRepository.update(application);
        auditService.logAction("UPDATE_APPLICATION");
    }

    public void deleteApplication(Application application) {
        applicationRepository.delete(application.getId());
        auditService.logAction("DELETE_APPLICATION");
    }

    public Application getApplicationDetails(int id) {
        auditService.logAction("GET_APPLICATION_DETAILS");
        return applicationRepository.findById(id).orElse(null);
    }

    public List<Project> listAllProjects() {
        auditService.logAction("LIST_ALL_PROJECTS");
        return projectRepository.findAll();
    }

    public List<Person> listAllParticipants() {
        auditService.logAction("LIST_ALL_PARTICIPANTS");
        return personRepository.findAll();
    }

    public List<Organization> listAllOrganizations() {
        auditService.logAction("LIST_ALL_ORGANIZATIONS");
        return organizationRepository.findAll();
    }

    public void approveApplication(Application application) {
        application.setStatus(ApplicationStatus.APPROVED);
        auditService.logAction("APPROVE_APPLICATION");
        updateApplication(application);
    }

    public void rejectApplication(Application application) {
        application.setStatus(ApplicationStatus.REJECTED);
        auditService.logAction("REJECT_APPLICATION");
        updateApplication(application);
    }

    public List<Application> listApplicationsForProject(Project project) {
        auditService.logAction("LIST_APPLICATIONS_FOR_PROJECT");
        return applicationRepository.findByProject(project.getId());
    }

    public List<Application> listApplicationsForPerson(Person person) {
        auditService.logAction("LIST_APPLICATIONS_FOR_PERSON");
        return applicationRepository.findByPerson(person.getId());
    }

    public List<Project> listProjectsByCountry(Country country) {
        auditService.logAction("LIST_PROJECTS_BY_COUNTRY");
        return projectRepository.findByCountry(country);
    }
}