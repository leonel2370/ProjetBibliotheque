package datastructures.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDateTime dateAdhesion;

    public Membre(int id, String nom, String prenom, String email, LocalDateTime dateAdhesion) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateAdhesion = dateAdhesion;
    }

    public void afficherDetails() {
        System.out.println("Membre [Nom=" + nom + ", Prénom=" + prenom + "]");
    }

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateAdhesion() {
        return dateAdhesion;
    }

    public void setDateAdhesion(LocalDateTime dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }
    // Getters et Setters...

}
