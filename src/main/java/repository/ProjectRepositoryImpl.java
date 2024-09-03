package repository;

import config.DatabaseConfiguration;
import entity.Project;
import entity.ProjectStatus;
import entity.Organization;
import entity.Country;
import entity.EuCountry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {
    private static ProjectRepositoryImpl instance = null;

    private static final String INSERT_QUERY = "INSERT INTO project (title, description, euid, status, start_date, end_date, organizer_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM project WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM project";
    private static final String UPDATE_QUERY = "UPDATE project SET title = ?, description = ?, euid = ?, status = ?, start_date = ?, end_date = ?, organizer_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM project WHERE id = ?";
    private static final String GET_LAST_ID_QUERY = "SELECT id FROM project ORDER BY id DESC LIMIT 1";
    private static final String SELECT_BY_COUNTRY_QUERY = "SELECT p.* FROM project p JOIN organization o ON p.organizer_id = o.id WHERE o.country = ?";

    private ProjectRepositoryImpl() {}

    public static ProjectRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ProjectRepositoryImpl();
        }
        return instance;
    }

    @Override
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
    public void save(Project project) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, project.getTitle());
            pstmt.setString(2, project.getDescription());
            pstmt.setString(3, project.getEuid());
            pstmt.setString(4, project.getStatus().toString());
            pstmt.setDate(5, Date.valueOf(project.getStartDate()));
            pstmt.setDate(6, Date.valueOf(project.getEndDate()));
            pstmt.setInt(7, project.getOrganizer().getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating project failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    project.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating project failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Project> findById(int id) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToProject(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                projects.add(mapResultSetToProject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public void update(Project project) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY)) {

            pstmt.setString(1, project.getTitle());
            pstmt.setString(2, project.getDescription());
            pstmt.setString(3, project.getEuid());
            pstmt.setString(4, project.getStatus().toString());
            pstmt.setDate(5, Date.valueOf(project.getStartDate()));
            pstmt.setDate(6, Date.valueOf(project.getEndDate()));
            pstmt.setInt(7, project.getOrganizer().getId());
            pstmt.setInt(8, project.getId());

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
    public List<Project> findByCountry(Country country) {
        List<Project> projects = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_COUNTRY_QUERY)) {

            pstmt.setString(1, country.toString());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(mapResultSetToProject(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    private Project mapResultSetToProject(ResultSet rs) throws SQLException {
        Project project = new Project(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("euid"),
                ProjectStatus.valueOf(rs.getString("status")),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("end_date").toLocalDate()
        );

        // Aici n-am stiut exact cum sa gestionez situatia, am lasat doar organizer_id si n-am mai tras toate datele dupa mine
        Organization organizer = new Organization(rs.getInt("organizer_id"), null, null, null);
        project.setOrganizer(organizer);

        return project;
    }
}