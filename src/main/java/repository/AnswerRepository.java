package repository;

import model.Answer;
import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepository {
    // Instance unique de la classe
    private static AnswerRepository instance;

    // Constructeur privé pour empêcher l'instanciation directe
    private AnswerRepository() {}

    // Méthode pour récupérer l'instance unique
    public static AnswerRepository getInstance() {
        if (instance == null) {
            synchronized (AnswerRepository.class) {
                if (instance == null) {
                    instance = new AnswerRepository();
                }
            }
        }
        return instance;
    }

    public List<Answer> getAnswersByQuestionId(int questionId) {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM answers WHERE question_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, questionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    answers.add(new Answer(
                            rs.getInt("id"),
                            rs.getInt("question_id"),
                            rs.getString("answer_text"),
                            rs.getBoolean("is_correct")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    public void addAnswer(Answer answer) {
        String query = "INSERT INTO answers (question_id, answer_text, is_correct) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, answer.getQuestionId());
            stmt.setString(2, answer.getAnswerText());
            stmt.setBoolean(3, answer.isCorrect());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
