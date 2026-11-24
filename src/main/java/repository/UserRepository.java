package repository;

import model.User;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    // Instance unique de la classe
    private static UserRepository instance;

    // Constructeur privé pour empêcher l'instanciation directe
    private UserRepository() {}

    // Méthode pour récupérer l'instance unique
    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public User getUserById(int id) {
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("email"),
                            rs.getDate("date_of_birth")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getDate("date_of_birth")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(User user) {
        String query = "INSERT INTO users (nom, email, date_of_birth) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setDate(3, user.getDateOfBirth());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> searchUsers(String name, String email, int minScore, int birthYear, int offset, int limit) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.id, u.nom, u.email, u.date_of_birth " +
                "FROM users u " +
                "LEFT JOIN results r ON u.id = r.user_id " +
                "WHERE (? IS NULL OR u.nom LIKE ?) " +
                "AND (? IS NULL OR u.email LIKE ?) " +
                "AND (? = 0 OR r.score >= ?) " +
                "AND (? = 0 OR EXTRACT(YEAR FROM u.date_of_birth) = ?) " +
                "GROUP BY u.id " +
                "ORDER BY u.nom ASC " +
                "LIMIT ? OFFSET ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, (name != null && !name.isEmpty()) ? name : null);
            stmt.setString(2, (name != null && !name.isEmpty()) ? "%" + name + "%" : null);
            stmt.setString(3, (email != null && !email.isEmpty()) ? email : null);
            stmt.setString(4, (email != null && !email.isEmpty()) ? "%" + email + "%" : null);
            stmt.setInt(5, minScore);
            stmt.setInt(6, minScore);
            stmt.setInt(7, birthYear);
            stmt.setInt(8, birthYear);
            stmt.setInt(9, limit);
            stmt.setInt(10, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("email"),
                            rs.getDate("date_of_birth")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public int getTotalCount(String name, String email, int minScore, int birthYear) {
        String query = "SELECT COUNT(DISTINCT u.id) AS totalCount " +
                "FROM users u " +
                "LEFT JOIN results r ON u.id = r.user_id " +
                "WHERE (? IS NULL OR u.nom LIKE ?) " +
                "AND (? IS NULL OR u.email LIKE ?) " +
                "AND (? = 0 OR r.score >= ?) " +
                "AND (? = 0 OR EXTRACT(YEAR FROM u.date_of_birth) = ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, (name != null && !name.isEmpty()) ? name : null);
            stmt.setString(2, (name != null && !name.isEmpty()) ? "%" + name + "%" : null);
            stmt.setString(3, (email != null && !email.isEmpty()) ? email : null);
            stmt.setString(4, (email != null && !email.isEmpty()) ? "%" + email + "%" : null);
            stmt.setInt(5, minScore);
            stmt.setInt(6, minScore);
            stmt.setInt(7, birthYear);
            stmt.setInt(8, birthYear);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("totalCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
