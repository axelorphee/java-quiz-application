package model;

import java.sql.Date;

public class User {
    private int id;
    private String nom;
    private String email;
    private Date dateOfBirth;


    // Constructeur
    public User(int id, String nom, String email, Date dateOfBirth) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.dateOfBirth = dateOfBirth;

    }

    // Constructeur sans ID pour la création
    public User(String nom, String email, Date dateOfBirth) {
        this.nom = nom;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
