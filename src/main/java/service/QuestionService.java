package service;

import model.Question;
import repository.QuestionRepository;

import java.sql.SQLException;
import java.util.List;

public class QuestionService {
    // Instance unique de la classe
    private static QuestionService instance;

    // Instance du repository
    private final QuestionRepository questionRepository = QuestionRepository.getInstance();

    // Constructeur privé pour empêcher l'instanciation directe
    private QuestionService() {}

    // Méthode pour récupérer l'instance unique
    public static QuestionService getInstance() {
        if (instance == null) {
            synchronized (QuestionService.class) {
                if (instance == null) {
                    instance = new QuestionService();
                }
            }
        }
        return instance;
    }

    public Question addQuestion(int testId, String questionText, String level) {
        Question question = new Question(testId, questionText, level);
        questionRepository.addQuestion(question);
        return question;
    }

    public List<Question> getQuestionsByTestId(int testId) {
        return questionRepository.getQuestionsByTestId(testId);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }
}
