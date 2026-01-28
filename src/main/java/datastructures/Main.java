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
        LivreDAO livreDAO = new LivreDAO();
        MembreDAO membreDAO = new MembreDAO();
        EmpruntDAO EmpruntDAO = new EmpruntDAO();

        int choix;
        do {
            System.out.println("\n--- GESTION BIBLIOTHÈQUE ---");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre");
            System.out.println("3. Inscrire un membre");
            System.out.println("4. Enregistrer un emprunt");
            System.out.println("5. Afficher les emprunts en retard");
            System.out.println("6. Quitter");
            System.out.print(" Votre Choix : ");

            choix = scanner.nextInt();
            scanner.nextLine();

            switch(choix) {
                case 1:
                    System.out.print("Titre : "); String t = scanner.nextLine();
                    System.out.print("Auteur : "); String a = scanner.nextLine();
                    System.out.print("Catégorie : "); String c = scanner.nextLine();
                    System.out.print("Nombre d'exemplaires : "); int n = scanner.nextInt();
                    livreDAO.ajouterLivre(new Livre(0, t, a, c, n));
                    break;
                case 2:
                    System.out.print("Titre à rechercher : ");
                    String search = scanner.nextLine();
                    List<Livre> results = livreDAO.rechercherParTitre(search);
                    results.forEach(l -> System.out.println(l.getId() + " - " + l.getTitre()));
                    break;
                case 3:
                    System.out.print("Nom : "); String nom = scanner.nextLine();
                    System.out.print("Prénom : "); String prenom = scanner.nextLine();
                    System.out.print("Email : "); String mail = scanner.nextLine();
                    membreDAO.inscrireMembre(new Membre(0, nom, prenom, mail, LocalDateTime.now()));
                    break;
                case 4:
                    System.out.print("Nom du membre : ");
                    String nomM = scanner.nextLine(); // On utilise nextLine() pour les noms
                    System.out.print("Titre du livre : ");
                    String titreL = scanner.nextLine();

                    // Appel de la méthode avec les Strings
                    EmpruntDAO.enregistrerEmprunt(nomM, titreL);
                    break;
                case 5:
                    EmpruntDAO.afficherEmpruntsEnRetard();
                    break;
                case 6:
                    System.out.println("Au revoir !");
                    break;
            }
        } while (choix != 6);
        scanner.close();
    }
}
