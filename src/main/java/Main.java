package application;

import entity.*;
import service.ApplicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ApplicationService service = ApplicationService.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        while (true) {
            displayMainMenu();
            int choice = getIntInput();
            processMainMenuChoice(choice);
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n----- Meniu Principal -----");
        System.out.println("1. Gestionare Organizatori");
        System.out.println("2. Gestionare Proiecte");
        System.out.println("3. Gestionare Participanți");
        System.out.println("4. Gestionare Aplicații");
        System.out.println("5. Listări și Rapoarte");
        System.out.println("0. Ieșire");
        System.out.print("Alegeți o opțiune: ");
    }

    private static void processMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                manageOrganizations();
                break;
            case 2:
                manageProjects();
                break;
            case 3:
                manageParticipants();
                break;
            case 4:
                manageApplications();
                break;
            case 5:
                showListingsAndReports();
                break;
            case 0:
                System.out.println("La revedere!");
                System.exit(0);
            default:
                System.out.println("Opțiune invalidă. Vă rugăm să încercați din nou.");
        }
    }

    private static void manageOrganizations() {
        while (true) {
            System.out.println("\n----- Gestionare Organizatori -----");
            System.out.println("1. Adăugare organizator");
            System.out.println("2. Actualizare organizator");
            System.out.println("3. Ștergere organizator");
            System.out.println("4. Afișare detalii organizator");
            System.out.println("0. Înapoi la meniul principal");
            System.out.print("Alegeți o opțiune: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addOrganization();
                    break;
                case 2:
                    updateOrganization();
                    break;
                case 3:
                    deleteOrganization();
                    break;
                case 4:
                    displayOrganizationDetails();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să încercați din nou.");
            }
        }
    }

    private static void manageProjects() {
        while (true) {
            System.out.println("\n----- Gestionare Proiecte -----");
            System.out.println("1. Adăugare proiect");
            System.out.println("2. Actualizare proiect");
            System.out.println("3. Ștergere proiect");
            System.out.println("4. Afișare detalii proiect");
            System.out.println("0. Înapoi la meniul principal");
            System.out.print("Alegeți o opțiune: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addProject();
                    break;
                case 2:
                    updateProject();
                    break;
                case 3:
                    deleteProject();
                    break;
                case 4:
                    displayProjectDetails();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să încercați din nou.");
            }
        }
    }

    private static void manageParticipants() {
        while (true) {
            System.out.println("\n----- Gestionare Participanți -----");
            System.out.println("1. Adăugare participant");
            System.out.println("2. Actualizare participant");
            System.out.println("3. Ștergere participant");
            System.out.println("4. Afișare detalii participant");
            System.out.println("0. Înapoi la meniul principal");
            System.out.print("Alegeți o opțiune: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addParticipant();
                    break;
                case 2:
                    updateParticipant();
                    break;
                case 3:
                    deleteParticipant();
                    break;
                case 4:
                    displayParticipantDetails();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să încercați din nou.");
            }
        }
    }

    private static void manageApplications() {
        while (true) {
            System.out.println("\n----- Gestionare Aplicații -----");
            System.out.println("1. Adăugare aplicație");
            System.out.println("2. Actualizare aplicație");
            System.out.println("3. Ștergere aplicație");
            System.out.println("4. Afișare detalii aplicație");
            System.out.println("5. Aprobare/Respingere aplicație");
            System.out.println("0. Înapoi la meniul principal");
            System.out.print("Alegeți o opțiune: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addApplication();
                    break;
                case 2:
                    updateApplication();
                    break;
                case 3:
                    deleteApplication();
                    break;
                case 4:
                    displayApplicationDetails();
                    break;
                case 5:
                    approveOrRejectApplication();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să încercați din nou.");
            }
        }
    }

    private static void showListingsAndReports() {
        while (true) {
            System.out.println("\n----- Listări și Rapoarte -----");
            System.out.println("1. Listare toate proiectele");
            System.out.println("2. Listare toți participanții");
            System.out.println("3. Listare toți organizatorii");
            System.out.println("4. Listare aplicații pentru un proiect");
            System.out.println("5. Listare aplicații pentru un participant");
            System.out.println("6. Listare proiecte după țară");
            System.out.println("0. Înapoi la meniul principal");
            System.out.print("Alegeți o opțiune: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    listAllProjects();
                    break;
                case 2:
                    listAllParticipants();
                    break;
                case 3:
                    listAllOrganizations();
                    break;
                case 4:
                    listApplicationsForProject();
                    break;
                case 5:
                    listApplicationsForParticipant();
                    break;
                case 6:
                    listProjectsByCountry();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să încercați din nou.");
            }
        }
    }

    private static void addOrganization() {
        System.out.println("\n----- Adăugare Organizator -----");
        System.out.print("Nume organizație: ");
        String name = getStringInput();
        System.out.print("Țară (ex: Romania, Germany, etc.): ");
        String countryName = getStringInput();
        Country country = EuCountry.valueOf(countryName);

        ContactInfo contact = new ContactInfo();
        System.out.print("Telefon: ");
        contact.setPhone(getStringInput());
        System.out.print("Email: ");
        contact.setEmail(getStringInput());

        Organization org = new Organization(service.listAllOrganizations().size() + 1, name, country, contact);
        service.addOrganization(org);
        System.out.println("Organizația a fost adăugată cu succes!");
    }

    private static void updateOrganization() {
        System.out.println("\n----- Actualizare Organizator -----");
        System.out.print("ID-ul organizației de actualizat: ");
        int id = getIntInput();
        Organization org = service.getOrganizationDetails(id);
        if (org == null) {
            System.out.println("Organizația nu a fost găsită.");
            return;
        }

        System.out.print("Nume nou (lăsați gol pentru a păstra numele actual): ");
        String name = getStringInput();
        if (!name.isEmpty()) {
            org.setName(name);
        }

        System.out.print("Țară nouă (lăsați gol pentru a păstra țara actuală): ");
        String countryName = getStringInput();
        if (!countryName.isEmpty()) {
            org.setCountry(EuCountry.valueOf(countryName));
        }

        ContactInfo contact = org.getContact();
        System.out.print("Telefon nou (lăsați gol pentru a păstra telefonul actual): ");
        String phone = getStringInput();
        if (!phone.isEmpty()) {
            contact.setPhone(phone);
        }

        System.out.print("Email nou (lăsați gol pentru a păstra emailul actual): ");
        String email = getStringInput();
        if (!email.isEmpty()) {
            contact.setEmail(email);
        }

        service.updateOrganization(org);
        System.out.println("Organizația a fost actualizată cu succes!");
    }

    private static void deleteOrganization() {
        System.out.println("\n----- Ștergere Organizator -----");
        System.out.print("ID-ul organizației de șters: ");
        int id = getIntInput();
        Organization org = service.getOrganizationDetails(id);
        if (org == null) {
            System.out.println("Organizația nu a fost găsită.");
            return;
        }

        service.deleteOrganization(org);
        System.out.println("Organizația a fost ștearsă cu succes!");
    }

    private static void displayOrganizationDetails() {
        System.out.println("\n----- Afișare Detalii Organizator -----");
        System.out.print("ID-ul organizației: ");
        int id = getIntInput();
        Organization org = service.getOrganizationDetails(id);
        if (org == null) {
            System.out.println("Organizația nu a fost găsită.");
            return;
        }

        System.out.println("Detalii organizație: \n" + org);
    }

    private static void addProject() {
        System.out.println("\n----- Adăugare Proiect -----");
        System.out.print("Titlu proiect: ");
        String title = getStringInput();
        System.out.print("Descriere proiect: ");
        String description = getStringInput();
        System.out.print("EUID proiect: ");
        String euid = getStringInput();
        System.out.print("Data de început (format: dd-MM-yyyy): ");
        LocalDate startDate = LocalDate.parse(getStringInput(), dateFormatter);
        System.out.print("Data de sfârșit (format: dd-MM-yyyy): ");
        LocalDate endDate = LocalDate.parse(getStringInput(), dateFormatter);

        Project project = new Project(service.listAllProjects().size() + 1, title, description, euid, ProjectStatus.PENDING, startDate, endDate);
        service.addProject(project);
        System.out.println("Proiectul a fost adăugat cu succes!");
    }

    private static void updateProject() {
        System.out.println("\n----- Actualizare Proiect -----");
        System.out.print("ID-ul proiectului de actualizat: ");
        int id = getIntInput();
        Project project = service.getProjectDetails(id);
        if (project == null) {
            System.out.println("Proiectul nu a fost găsit.");
            return;
        }

        System.out.print("Titlu nou (lăsați gol pentru a păstra titlul actual): ");
        String title = getStringInput();
        if (!title.isEmpty()) {
            project.setTitle(title);
        }

        System.out.print("Descriere nouă (lăsați gol pentru a păstra descrierea actuală): ");
        String description = getStringInput();
        if (!description.isEmpty()) {
            project.setDescription(description);
        }

        System.out.print("Status nou (PENDING/ONGOING/FINISHED): ");
        String status = getStringInput();
        if (!status.isEmpty()) {
            project.setStatus(ProjectStatus.valueOf(status.toUpperCase()));
        }

        service.updateProject(project);
        System.out.println("Proiectul a fost actualizat cu succes!");
    }

    private static void deleteProject() {
        System.out.println("\n----- Ștergere Proiect -----");
        System.out.print("ID-ul proiectului de șters: ");
        int id = getIntInput();
        Project project = service.getProjectDetails(id);
        if (project == null) {
            System.out.println("Proiectul nu a fost găsit.");
            return;
        }

        service.deleteProject(project);
        System.out.println("Proiectul a fost șters cu succes!");
    }

    private static void displayProjectDetails() {
        System.out.println("\n----- Afișare Detalii Proiect -----");
        System.out.print("ID-ul proiectului: ");
        int id = getIntInput();
        Project project = service.getProjectDetails(id);
        if (project == null) {
            System.out.println("Proiectul nu a fost găsit.");
            return;
        }

        System.out.println("Detalii proiect: \n" + project);
    }

    static void addParticipant() {
        System.out.println("\n----- Adăugare Participant -----");
        System.out.print("Nume participant: ");
        String name = getStringInput();
        System.out.print("Țară (ex: Romania, Germany, etc.): ");
        String countryName = getStringInput();
        Country country = EuCountry.valueOf(countryName);
        System.out.print("Data nașterii (format: dd-MM-yyyy): ");
        LocalDate dateOfBirth = LocalDate.parse(getStringInput(), dateFormatter);

        ContactInfo contact = new ContactInfo();
        System.out.print("Telefon: ");
        contact.setPhone(getStringInput());
        System.out.print("Email: ");
        contact.setEmail(getStringInput());

        int age = LocalDate.now().getYear() - dateOfBirth.getYear();
        Person person = new Person(service.listAllParticipants().size() + 1, country, name, dateOfBirth, age, contact);
        service.addPerson(person);
        System.out.println("Participantul a fost adăugat cu succes!");
    }

    private static void updateParticipant() {
        System.out.println("\n----- Actualizare Participant -----");
        System.out.print("ID-ul participantului de actualizat: ");
        int id = getIntInput();
        Person person = service.getPersonDetails(id);
        if (person == null) {
            System.out.println("Participantul nu a fost găsit.");
            return;
        }

        System.out.print("Nume nou (lăsați gol pentru a păstra numele actual): ");
        String name = getStringInput();
        if (!name.isEmpty()) {
            person.setName(name);
        }

        System.out.print("Țară nouă (lăsați gol pentru a păstra țara actuală): ");
        String countryName = getStringInput();
        if (!countryName.isEmpty()) {
            person.setCountry(EuCountry.valueOf(countryName));
        }

        ContactInfo contact = person.getContact();
        System.out.print("Telefon nou (lăsați gol pentru a păstra telefonul actual): ");
        String phone = getStringInput();
        if (!phone.isEmpty()) {
            contact.setPhone(phone);
        }

        System.out.print("Email nou (lăsați gol pentru a păstra emailul actual): ");
        String email = getStringInput();
        if (!email.isEmpty()) {
            contact.setEmail(email);
        }

        service.updatePerson(person);
        System.out.println("Participantul a fost actualizat cu succes!");
    }

    private static void deleteParticipant() {
        System.out.println("\n----- Ștergere Participant -----");
        System.out.print("ID-ul participantului de șters: ");
        int id = getIntInput();
        Person person = service.getPersonDetails(id);
        if (person == null) {
            System.out.println("Participantul nu a fost găsit.");
            return;
        }

        service.deletePerson(person);
        System.out.println("Participantul a fost șters cu succes!");
    }

    private static void displayParticipantDetails() {
        System.out.println("\n----- Afișare Detalii Participant -----");
        System.out.print("ID-ul participantului: ");
        int id = getIntInput();
        Person person = service.getPersonDetails(id);
        if (person == null) {
            System.out.println("Participantul nu a fost găsit.");
            return;
        }

        System.out.println("Detalii participant:");
        System.out.println("ID: " + person.getId());
        System.out.println("Nume: " + person.getName());
        System.out.println("Țară: " + person.getCountry());
        System.out.println("Data nașterii: " + person.getDateOfBirth().format(dateFormatter));
        System.out.println("Vârstă: " + person.getAge());
        System.out.println("Telefon: " + person.getContact().getPhone());
        System.out.println("Email: " + person.getContact().getEmail());
    }

    private static void addApplication() {
        System.out.println("\n----- Adăugare Aplicație -----");
        System.out.print("ID-ul participantului: ");
        int personId = getIntInput();
        Person person = service.getPersonDetails(personId);
        if (person == null) {
            System.out.println("Participantul nu a fost găsit.");
            return;
        }

        System.out.print("ID-ul proiectului: ");
        int projectId = getIntInput();
        Project project = service.getProjectDetails(projectId);
        if (project == null) {
            System.out.println("Proiectul nu a fost găsit.");
            return;
        }

        System.out.print("Detalii aplicație: ");
        String details = getStringInput();

        Application application = new Application();
        application.setId(service.listAllParticipants().size() + 1);
        application.setPerson(person);
        application.setProject(project);
        application.setStatus(ApplicationStatus.SUBMITTED);
        application.setSubmissionDate(LocalDateTime.now());
        application.setDetails(details);

        service.addApplication(application);
        System.out.println("Aplicația a fost adăugată cu succes!");
    }

    private static void updateApplication() {
        System.out.println("\n----- Actualizare Aplicație -----");
        System.out.print("ID-ul aplicației de actualizat: ");
        int id = getIntInput();
        Application application = service.getApplicationDetails(id);
        if (application == null) {
            System.out.println("Aplicația nu a fost găsită.");
            return;
        }

        System.out.print("Detalii noi (lăsați gol pentru a păstra detaliile actuale): ");
        String details = getStringInput();
        if (!details.isEmpty()) {
            application.setDetails(details);
        }

        service.updateApplication(application);
        System.out.println("Aplicația a fost actualizată cu succes!");
    }

    private static void deleteApplication() {
        System.out.println("\n----- Ștergere Aplicație -----");
        System.out.print("ID-ul aplicației de șters: ");
        int id = getIntInput();
        Application application = service.getApplicationDetails(id);
        if (application == null) {
            System.out.println("Aplicația nu a fost găsită.");
            return;
        }

        service.deleteApplication(application);
        System.out.println("Aplicația a fost ștearsă cu succes!");
    }

    private static void displayApplicationDetails() {
        System.out.println("\n----- Afișare Detalii Aplicație -----");
        System.out.print("ID-ul aplicației: ");
        int id = getIntInput();
        Application application = service.getApplicationDetails(id);
        if (application == null) {
            System.out.println("Aplicația nu a fost găsită.");
            return;
        }

        System.out.println("Detalii aplicație: \n" + application);

    }

    private static void approveOrRejectApplication() {
        System.out.println("\n----- Aprobare/Respingere Aplicație -----");
        System.out.print("ID-ul aplicației: ");
        int id = getIntInput();
        Application application = service.getApplicationDetails(id);
        if (application == null) {
            System.out.println("Aplicația nu a fost găsită.");
            return;
        }

        System.out.print("Aprobați (A) sau Respingeți (R) aplicația? ");
        String decision = getStringInput().toUpperCase();
        if (decision.equals("A")) {
            service.approveApplication(application);
            System.out.println("Aplicația a fost aprobată.");
        } else if (decision.equals("R")) {
            service.rejectApplication(application);
            System.out.println("Aplicația a fost respinsă.");
        } else {
            System.out.println("Opțiune invalidă.");
        }
    }

    private static void listAllProjects() {
        List<Project> projects = service.listAllProjects();
        System.out.println("\n----- Toate Proiectele -----");
        for (Project project : projects) {
            System.out.println(project);
        }
    }

    private static void listAllParticipants() {
        List<Person> participants = service.listAllParticipants();
        System.out.println("\n----- Toți Participanții -----");
        for (Person participant : participants) {
            System.out.println(participant);
        }
    }

    private static void listAllOrganizations() {
        List<Organization> organizations = service.listAllOrganizations();
        System.out.println("\n----- Toți Organizatorii -----");
        for (Organization organization : organizations) {
            System.out.println(organization);
        }
    }

    private static void listApplicationsForProject() {
        System.out.println("\n----- Listare Aplicații pentru Proiect -----");
        System.out.print("ID-ul proiectului: ");
        int id = getIntInput();
        Project project = service.getProjectDetails(id);
        if (project == null) {
            System.out.println("Proiectul nu a fost găsit.");
            return;
        }

        List<Application> applications = service.listApplicationsForProject(project);
        if (applications.isEmpty()) {
            System.out.println("Nu există aplicații pentru acest proiect.");
        } else {
            System.out.println("Aplicații pentru proiectul " + project.getTitle() + ":");
            for (Application app : applications) {
                System.out.println(app);
            }
        }
    }

    private static void listApplicationsForParticipant() {
        System.out.println("\n----- Listare Aplicații pentru Participant -----");
        System.out.print("ID-ul participantului: ");
        int id = getIntInput();
        Person person = service.getPersonDetails(id);
        if (person == null) {
            System.out.println("Participantul nu a fost găsit.");
            return;
        }

        List<Application> applications = service.listApplicationsForPerson(person);
        if (applications.isEmpty()) {
            System.out.println("Nu există aplicații pentru acest participant.");
        } else {
            System.out.println("Aplicații pentru participantul " + person.getName() + ":");
            for (Application app : applications) {
                System.out.println(app);
            }
        }
    }

    private static void listProjectsByCountry() {
        System.out.println("\n----- Listare Proiecte după Țară -----");
        System.out.print("Țară (ex: Romania, Germany, etc.): ");
        String countryName = getStringInput();
        Country country = EuCountry.valueOf(countryName);

        List<Project> projects = service.listProjectsByCountry(country);
        if (projects.isEmpty()) {
            System.out.println("Nu există proiecte pentru această țară.");
        } else {
            System.out.println("Proiecte în " + countryName + ":");
            for (Project project : projects) {
                System.out.println(project);
            }
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vă rugăm să introduceți un număr valid.");
            }
        }
    }

    private static String getStringInput() {
        return scanner.nextLine();
    }
}