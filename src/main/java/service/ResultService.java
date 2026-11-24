package service;

import config.PointConfig;
import model.Answer;
import model.Result;
import model.User;
import repository.ResultRepository;

import java.util.List;

public class ResultService {
    // Instance unique de la classe
    private static ResultService instance;

    // Instance du repository
    private final ResultRepository resultRepository = ResultRepository.getInstance();

    // Constructeur privé pour empêcher l'instanciation directe
    private ResultService() {}

    // Méthode pour récupérer l'instance unique
    public static ResultService getInstance() {
        if (instance == null) {
            synchronized (ResultService.class) {
                if (instance == null) {
                    instance = new ResultService();
                }
            }
        }
        return instance;
    }

    public void addResult(int userId, int testId, int score) {
        Result result = new Result(userId, testId, score);
        resultRepository.addResult(result);
    }

    public int calculateScore(List<Answer> userAnswers) {
        int score = 0;

        for (Answer answer : userAnswers) {
            if (answer.isCorrect()) {
                score += PointConfig.POINTS_FOR_CORRECT_ANSWER;
            } else {
                score += PointConfig.POINTS_FOR_WRONG_ANSWER;
            }
        }

        return score;
    }

    public double getAverageScore() {
        List<Result> results = resultRepository.getAllResults();
        return results.stream()
                .mapToInt(Result::getScore)
                .average()
                .orElse(0);
    }

    public List<User> getTopUsers(int limit) {
        return resultRepository.getTopUsers(limit);
    }

    public List<Result> getResultsByUserId(int userId) {
        return resultRepository.getResultsByUserId(userId);
    }
}
