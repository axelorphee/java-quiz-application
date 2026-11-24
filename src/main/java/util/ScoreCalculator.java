package util;

import config.PointConfig;
import model.Answer;

import java.util.List;

public class ScoreCalculator {
    public static int calculateScore(List<Answer> answers) {
        int score = 0;

        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                score += PointConfig.POINTS_FOR_CORRECT_ANSWER;
            } else {
                score += PointConfig.POINTS_FOR_WRONG_ANSWER;
            }
        }

        return score;
    }
}
