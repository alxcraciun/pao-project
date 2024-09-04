import entity.*;
import repository.*;
import java.util.*;

public class ApplicationService {
    private static ApplicationService instance;

    private OrganizationRepository organizationRepository;
    private ProjectRepository projectRepository;
    private PersonRepository personRepository;
    private ApplicationRepository applicationRepository;

    private ApplicationService() {
        organizationRepository = OrganizationRepositoryImpl.getInstance();
        projectRepository = ProjectRepositoryImpl.getInstance();
        personRepository = PersonRepositoryImpl.getInstance();
        applicationRepository = ApplicationRepositoryImpl.getInstance();
    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }

    public void addOrganization(Organization org) {
        organizationRepository.save(org);
    }

    public void updateOrganization(Organization org) {
        organizationRepository.update(org);
    }

    public void deleteOrganization(Organization org) {
        organizationRepository.delete(org.getId());
    }

    public Organization getOrganizationDetails(int id) {
        return organizationRepository.findById(id).orElse(null);
    }

    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public void updateProject(Project project) {
        projectRepository.update(project);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project.getId());
    }

    public Project getProjectDetails(int id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public void updatePerson(Person person) {
        personRepository.update(person);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person.getId());
    }

    public Person getPersonDetails(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public void addApplication(Application application) {
        applicationRepository.save(application);
    }

    public void updateApplication(Application application) {
        applicationRepository.update(application);
    }

    public void deleteApplication(Application application) {
        applicationRepository.delete(application.getId());
    }

    public Application getApplicationDetails(int id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public List<Project> listAllProjects() {
        return projectRepository.findAll();
    }

    public List<Person> listAllParticipants() {
        return personRepository.findAll();
    }

    public List<Organization> listAllOrganizations() {
        return organizationRepository.findAll();
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
        return applicationRepository.findByProject(project.getId());
    }

    public List<Application> listApplicationsForPerson(Person person) {
        return applicationRepository.findByPerson(person.getId());
    }

    public List<Project> listProjectsByCountry(Country country) {
        return projectRepository.findByCountry(country);
    }
}