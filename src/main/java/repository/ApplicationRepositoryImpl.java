package repository;

import config.DatabaseConfiguration;
import entity.Application;
import entity.ApplicationStatus;
import entity.Person;
import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationRepositoryImpl implements ApplicationRepository {
    private static ApplicationRepositoryImpl instance = null;

    private static final String INSERT_QUERY = "INSERT INTO application (person_id, project_id, status, submission_date, details) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM application WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM application";
    private static final String UPDATE_QUERY = "UPDATE application SET person_id = ?, project_id = ?, status = ?, submission_date = ?, details = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM application WHERE id = ?";
    private static final String GET_LAST_ID_QUERY = "SELECT id FROM application ORDER BY id DESC LIMIT 1";
    private static final String SELECT_BY_PROJECT_QUERY = "SELECT * FROM application WHERE project_id = ?";
    private static final String SELECT_BY_PERSON_QUERY = "SELECT * FROM application WHERE person_id = ?";

    private ApplicationRepositoryImpl() {
    }

    public static ApplicationRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ApplicationRepositoryImpl();
        }
        return instance;
    }

    public int getLastId() {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(GET_LAST_ID_QUERY);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public void save(Application application) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, application.getPerson().getId());
            pstmt.setInt(2, application.getProject().getId());
            pstmt.setString(3, application.getStatus().toString());
            pstmt.setTimestamp(4, Timestamp.valueOf(application.getSubmissionDate()));
            pstmt.setString(5, application.getDetails());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating application failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    application.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating application failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Application> findById(int id) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToApplication(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Application> findAll() {
        List<Application> applications = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                applications.add(mapResultSetToApplication(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    @Override
    public void update(Application application) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY)) {

            pstmt.setInt(1, application.getPerson().getId());
            pstmt.setInt(2, application.getProject().getId());
            pstmt.setString(3, application.getStatus().toString());
            pstmt.setTimestamp(4, Timestamp.valueOf(application.getSubmissionDate()));
            pstmt.setString(5, application.getDetails());
            pstmt.setInt(6, application.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_QUERY)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Application> findByProject(int projectId) {
        List<Application> applications = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_PROJECT_QUERY)) {

            pstmt.setInt(1, projectId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    applications.add(mapResultSetToApplication(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    @Override
    public List<Application> findByPerson(int personId) {
        List<Application> applications = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_PERSON_QUERY)) {

            pstmt.setInt(1, personId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    applications.add(mapResultSetToApplication(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    private Application mapResultSetToApplication(ResultSet rs) throws SQLException {
        Application application = new Application();
        application.setId(rs.getInt("id"));
        application.setStatus(ApplicationStatus.valueOf(rs.getString("status")));
        application.setSubmissionDate(rs.getTimestamp("submission_date").toLocalDateTime());
        application.setDetails(rs.getString("details"));

        // Aici n-am stiut exact cum sa gestionez situatia, am lasat doar person_id si project_id si n-am mai tras toate datele dupa mine
        Person person = new Person(rs.getInt("person_id"), null, null, null, 0, null);
        Project project = new Project(rs.getInt("project_id"), null, null, null, null, null, null);
        application.setPerson(person);
        application.setProject(project);

        return application;
    }
}