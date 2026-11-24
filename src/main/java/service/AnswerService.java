package service;

import model.Answer;
import repository.AnswerRepository;

import java.util.List;

public class AnswerService {
    // Instance unique de la classe
    private static AnswerService instance;

    // Instance du repository
    private final AnswerRepository answerRepository = AnswerRepository.getInstance();

    // Constructeur privé pour empêcher l'instanciation directe
    private AnswerService() {}

    // Méthode pour récupérer l'instance unique
    public static AnswerService getInstance() {
        if (instance == null) {
            synchronized (AnswerService.class) {
                if (instance == null) {
                    instance = new AnswerService();
                }
            }
        }
        return instance;
    }

    public void addAnswer(int questionId, String answerText, boolean isCorrect) {
        // Valider automatiquement si c’est la première ou deuxième réponse
        if (isCorrect) {
            System.out.println("Réponse correcte ajoutée : " + answerText);
        } else {
            System.out.println("Réponse incorrecte ajoutée : " + answerText);
        }

        Answer answer = new Answer(questionId, answerText, isCorrect);
        answerRepository.addAnswer(answer);
    }

    public List<Answer> getAnswersByQuestionId(int questionId) {
        return answerRepository.getAnswersByQuestionId(questionId);
    }
}
