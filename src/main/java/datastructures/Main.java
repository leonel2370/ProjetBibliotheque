package datastructures;

import datastructures.dao.EmpruntDAO;
import datastructures.dao.LivreDAO;
import datastructures.dao.MembreDAO;
import datastructures.model.Livre;
import datastructures.model.Membre;

import java.time.LocalDateTime;
import java.util.Scanner;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciation des services DAO
        LivreDAO livreDAO = new LivreDAO();
        MembreDAO membreDAO = new MembreDAO();
        EmpruntDAO empruntDAO = new EmpruntDAO();

        int choix;

        do {
            System.out.println("\n--- GESTION BIBLIOTHÈQUE ---");
            System.out.println("1. Ajouter un Livre");
            System.out.println("2. Inscrire un Membre");
            System.out.println("3. Emprunter un Livre");
            System.out.println("4. Retourner un Livre");
            System.out.println("5. Rechercher un Livre par titre");
            System.out.println("6. Quitter");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch(choix) {
                case 1:
                    System.out.print("Titre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur : ");
                    String auteur = scanner.nextLine();
                    System.out.print("Catégorie : ");
                    String cat = scanner.nextLine();
                    System.out.print("Nombre d'exemplaires : ");
                    int nb = scanner.nextInt();

                    Livre nouveauLivre = new Livre(0, titre, auteur, cat, nb);
                    livreDAO.ajouterLivre(nouveauLivre);
                    break;

                case 2:
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();

                    Membre nouveauMembre = new Membre(0, nom, prenom, email, LocalDateTime.now());
                    membreDAO.inscrireMembre(nouveauMembre);
                    break;

                case 3:
                    System.out.print("ID du Membre : ");
                    int idMembre = scanner.nextInt();
                    System.out.print("ID du Livre : ");
                    int idLivre = scanner.nextInt();

                    empruntDAO.enregistrerEmprunt(idMembre, idLivre);
                    break;

                case 4:
                    System.out.print("ID de l'emprunt à clôturer : ");
                    int idEmprunt = scanner.nextInt();

                    empruntDAO.gererRetour(idEmprunt);
                    break;

                case 5:
                    System.out.print("Entrez le titre (ou partie du titre) : ");
                    String recherche = scanner.nextLine();
                    List<Livre> livres = livreDAO.rechercherParTitre(recherche);

                    System.out.println("\nRésultats de la recherche :");
                    for (Livre l : livres) {
                        System.out.println(l.getId() + " - " + l.getTitre() + " (" + l.getAuteur() + ")");
                    }
                    break;

                case 6:
                    System.out.println("Fermeture de l'application...");
                    break;

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 6);

        scanner.close();
    }
}
