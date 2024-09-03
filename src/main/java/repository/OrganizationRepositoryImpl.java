package repository;

import config.DatabaseConfiguration;
import entity.Organization;
import entity.ContactInfo;
import entity.EuCountry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizationRepositoryImpl implements OrganizationRepository {
    private static OrganizationRepositoryImpl instance = null;

    private static final String INSERT_QUERY = "INSERT INTO organization (name, country, phone, email) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM organization WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM organization";
    private static final String UPDATE_QUERY = "UPDATE organization SET name = ?, country = ?, phone = ?, email = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM organization WHERE id = ?";
    private static final String GET_LAST_ID_QUERY = "SELECT id FROM organization ORDER BY id DESC LIMIT 1";

    private OrganizationRepositoryImpl() {
    }

    public static OrganizationRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new OrganizationRepositoryImpl();
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
    public void save(Organization organization) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, organization.getName());
            pstmt.setString(2, organization.getCountry().toString());
            pstmt.setString(3, organization.getContact().getPhone());
            pstmt.setString(4, organization.getContact().getEmail());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating organization failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    organization.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating organization failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Organization> findById(int id) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToOrganization(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Organization> findAll() {
        List<Organization> organizations = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                organizations.add(mapResultSetToOrganization(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organizations;
    }

    @Override
    public void update(Organization organization) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY)) {

            pstmt.setString(1, organization.getName());
            pstmt.setString(2, organization.getCountry().toString());
            pstmt.setString(3, organization.getContact().getPhone());
            pstmt.setString(4, organization.getContact().getEmail());
            pstmt.setInt(5, organization.getId());

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

    private Organization mapResultSetToOrganization(ResultSet rs) throws SQLException {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setPhone(rs.getString("phone"));
        contactInfo.setEmail(rs.getString("email"));

        return new Organization(
                rs.getInt("id"),
                rs.getString("name"),
                EuCountry.valueOf(rs.getString("country")),
                contactInfo
        );
    }
}