package datastructures;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n--- GESTION BIBLIOTHÈQUE ---");
            System.out.println("1. Ajouter un Livre");
            System.out.println("2. Inscrire un Membre");
            System.out.println("3. Emprunter un Livre");
            System.out.println("4. Retourner un Livre");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();

            switch(choix) {
                case 1:
                    // Appeler LivreDAO.ajouter()
                    break;
                case 3:
                    // Demander ID membre et ID livre
                    // Appeler EmpruntDAO.enregistrerEmprunt()
                    break;
                // ... autres cas
            }
        } while (choix != 5);
    
    }
}