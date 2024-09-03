package repository;

import config.DatabaseConfiguration;
import entity.Person;
import entity.ContactInfo;
import entity.EuCountry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {
    private static PersonRepositoryImpl instance = null;

    private static final String INSERT_QUERY = "INSERT INTO person (name, country, date_of_birth, age, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM person WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM person";
    private static final String UPDATE_QUERY = "UPDATE person SET name = ?, country = ?, date_of_birth = ?, age = ?, phone = ?, email = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM person WHERE id = ?";
    private static final String SELECT_BY_COUNTRY_QUERY = "SELECT * FROM person WHERE country = ?";
    private static final String GET_LAST_ID_QUERY = "SELECT id FROM person ORDER BY id DESC LIMIT 1";

    private PersonRepositoryImpl() {
    }

    public static PersonRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PersonRepositoryImpl();
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
    public void save(Person person) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getCountry().toString());
            pstmt.setDate(3, Date.valueOf(person.getDateOfBirth()));
            pstmt.setInt(4, person.getAge());
            pstmt.setString(5, person.getContact().getPhone());
            pstmt.setString(6, person.getContact().getEmail());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating person failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Person> findById(int id) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToPerson(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                persons.add(mapResultSetToPerson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public void update(Person person) {
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY)) {

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getCountry().toString());
            pstmt.setDate(3, Date.valueOf(person.getDateOfBirth()));
            pstmt.setInt(4, person.getAge());
            pstmt.setString(5, person.getContact().getPhone());
            pstmt.setString(6, person.getContact().getEmail());
            pstmt.setInt(7, person.getId());

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
    public List<Person> findByCountry(String country) {
        List<Person> persons = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_COUNTRY_QUERY)) {

            pstmt.setString(1, country);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    persons.add(mapResultSetToPerson(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    private Person mapResultSetToPerson(ResultSet rs) throws SQLException {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setPhone(rs.getString("phone"));
        contactInfo.setEmail(rs.getString("email"));

        return new Person(
                rs.getInt("id"),
                EuCountry.valueOf(rs.getString("country")),
                rs.getString("name"),
                rs.getDate("date_of_birth").toLocalDate(),
                rs.getInt("age"),
                contactInfo
        );
    }
}