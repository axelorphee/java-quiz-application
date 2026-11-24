package repository;

import model.Question;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {
    // Instance unique de la classe
    private static QuestionRepository instance;

    // Constructeur privé pour empêcher l'instanciation directe
    private QuestionRepository() {}

    // Méthode pour récupérer l'instance unique
    public static QuestionRepository getInstance() {
        if (instance == null) {
            synchronized (QuestionRepository.class) {
                if (instance == null) {
                    instance = new QuestionRepository();
                }
            }
        }
        return instance;
    }

    public List<Question> getQuestionsByTestId(int testId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions WHERE test_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, testId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    questions.add(new Question(
                            rs.getInt("id"),
                            rs.getInt("test_id"),
                            rs.getString("question_text"),
                            rs.getString("level")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                questions.add(new Question(
                        rs.getInt("id"),
                        rs.getInt("test_id"),
                        rs.getString("question_text"),
                        rs.getString("level")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public void addQuestion(Question question) {
        String query = "INSERT INTO questions (test_id, question_text, level) VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, question.getTestId());
            stmt.setString(2, question.getQuestionText());
            stmt.setString(3, question.getLevel());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    question.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
