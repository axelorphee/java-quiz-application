package repository;

import model.Test;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestRepository {
    // Instance unique de la classe
    private static TestRepository instance;

    // Constructeur privé pour empêcher l'instanciation directe
    private TestRepository() {}

    // Méthode pour récupérer l'instance unique
    public static TestRepository getInstance() {
        if (instance == null) {
            synchronized (TestRepository.class) {
                if (instance == null) {
                    instance = new TestRepository();
                }
            }
        }
        return instance;
    }

    public Test getTestById(int id) {
        Test test = null;
        String query = "SELECT * FROM tests WHERE id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    test = new Test(
                            rs.getInt("id"),
                            rs.getString("theme"),
                            rs.getInt("passinggrade")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        String query = "SELECT * FROM tests";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tests.add(new Test(
                        rs.getInt("id"),
                        rs.getString("theme"),
                        rs.getInt("passinggrade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public void addTest(Test test) {
        String query = "INSERT INTO tests (theme, passinggrade) VALUES (?, ?) RETURNING id";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, test.getTheme());
            stmt.setInt(2, test.getPassingGrade());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    test.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
