package datastructures.model;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String categorie;
    private int nbExemplaires;

    // Constructeurs, Getters et Setters
    public Livre(int id, String titre, String auteur, String categorie, int nombre_exeplaire) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nbExemplaires = nombre_exeplaire;
    }

    public void afficherDetails() {
        System.out.println("Livre [ID=" + id + ", Titre=" + titre + ", Auteur=" + auteur + "]");
    }

    // Getters et Setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNbExemplaires() {
        return nbExemplaires;
    }

    public void setNbExemplaires(int nbExemplaires) {
        this.nbExemplaires = nbExemplaires;
    }
}
