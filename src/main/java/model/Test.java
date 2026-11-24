package model;

public class Test {
    private int id;
    private String theme;
    private int passingGrade;

    // Constructeur
    public Test(int id, String theme, int passingGrade) {
        this.id = id;
        this.theme = theme;
        this.passingGrade = passingGrade;
    }

    // Constructeur sans ID pour la création
    public Test(String theme, int passingGrade) {
        this.theme = theme;
        this.passingGrade = passingGrade;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(int passingGrade) {
        this.passingGrade = passingGrade;
    }
}
