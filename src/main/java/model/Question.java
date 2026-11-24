package model;

public class Question {
    private int id;
    private int testId;
    private String questionText;
    private String level;

    // Constructeur
    public Question(int id, int testId, String questionText, String level) {
        this.id = id;
        this.testId = testId;
        this.questionText = questionText;
        this.level = level;
    }

    // Constructeur sans ID pour la création
    public Question(int testId, String questionText, String level) {
        this.testId = testId;
        this.questionText = questionText;
        this.level = level;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
