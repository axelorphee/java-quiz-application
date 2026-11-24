package repository;

import model.Result;
import model.User;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultRepository {
    // Instance unique de la classe
    private static ResultRepository instance;

    // Constructeur privé pour empêcher l'instanciation directe
    private ResultRepository() {}

    // Méthode pour récupérer l'instance unique
    public static ResultRepository getInstance() {
        if (instance == null) {
            synchronized (ResultRepository.class) {
                if (instance == null) {
                    instance = new ResultRepository();
                }
            }
        }
        return instance;
    }

    public void addResult(Result result) {
        String query = "INSERT INTO results (user_id, test_id, score, date_passed) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, result.getUserId());
            stmt.setInt(2, result.getTestId());
            stmt.setInt(3, result.getScore());
            stmt.setTimestamp(4, result.getDatePassed());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Result> getResultsByUserId(int userId) {
        List<Result> results = new ArrayList<>();
        String query = "SELECT * FROM results WHERE user_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Result(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("test_id"),
                            rs.getInt("score"),
                            rs.getTimestamp("date_passed")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public double getAverageTestsPerUser() {
        String query = "SELECT AVG(test_count) FROM (SELECT COUNT(*) AS test_count FROM results GROUP BY user_id) subquery";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getAverageScorePerTest() {
        String query = "SELECT AVG(score) FROM results";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();
        String query = "SELECT * FROM results";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                results.add(new Result(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("test_id"),
                        rs.getInt("score"),
                        rs.getTimestamp("date_passed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public List<User> getTopUsers(int limit) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.id, u.nom, u.email, u.date_of_birth, AVG(r.score) AS avg_score " +
                "FROM users u " +
                "JOIN results r ON u.id = r.user_id " +
                "GROUP BY u.id " +
                "ORDER BY avg_score DESC " +
                "LIMIT ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, limit);

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
}
