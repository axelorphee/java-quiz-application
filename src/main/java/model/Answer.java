package model;

public class Answer {
    private int id;
    private int questionId;
    private String answerText;
    private boolean isCorrect;

    // Constructeur
    public Answer(int id, int questionId, String answerText, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    // Constructeur sans ID pour la création
    public Answer(int questionId, String answerText, boolean isCorrect) {
        this.questionId = questionId;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
