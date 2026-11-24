package model;

import java.sql.Timestamp;

public class Result {
    private int id;
    private int userId;
    private int testId;
    private int score;
    private Timestamp datePassed;

    // Constructeur
    public Result(int id, int userId, int testId, int score, Timestamp datePassed) {
        this.id = id;
        this.userId = userId;
        this.testId = testId;
        this.score = score;
        this.datePassed = datePassed;
    }

    // Constructeur sans ID pour la création
    public Result(int userId, int testId, int score) {
        this.userId = userId;
        this.testId = testId;
        this.score = score;
        this.datePassed = new Timestamp(System.currentTimeMillis());
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getDatePassed() {
        return datePassed;
    }

    public void setDatePassed(Timestamp datePassed) {
        this.datePassed = datePassed;
    }
}
