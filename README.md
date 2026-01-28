# ProjetBibliotheque
projet de consildation des acquis sur la programmation oriente objet avec  java pour la formation k48
📚 Système de Gestion de Bibliothèque (Java/PostgreSQL)

Ce projet est une application de gestion de bibliothèque réalisée en Java, utilisant une base de données PostgreSQL pour la persistance des données. Elle permet de gérer les livres, les membres, les emprunts et le calcul automatique des pénalités de retard.
🛠️ Fonctionnalités

    Gestion des Livres : Ajout, recherche par titre/catégorie, suivi des exemplaires.

    Gestion des Membres : Inscription et suivi des dates d'adhésion.

    Gestion des Emprunts : Enregistrement des prêts et gestion des retours.

    Calcul des Pénalités : Calcul automatique de 100 F CFA par jour de retard.

🗄️ Structure de la Base de Données

Le projet utilise trois tables principales :

    livres : Stocke les informations sur les ouvrages.

    membres : Gère les informations des utilisateurs inscrits.

    emprunts : Lie les membres aux livres avec les dates clés.

 Installation et Configuration
1. Prérequis

    Java JDK 21 ou supérieur.

    PostgreSQL installé et configuré.

    Driver JDBC PostgreSQL (postgresql-42.7.9.jar).

2. Configuration de la Base de Données

Exécutez le script suivant dans votre terminal PostgreSQL :
SQL

-- 1. Création de la base de données
CREATE DATABASE gestion_bibliotheque;

-- 2. Table des Livres 
CREATE TABLE livres (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255) NOT NULL,
    categorie VARCHAR(100),
    nombre_exemplaires INTEGER DEFAULT 1
);

-- 3. Table des Membres 
CREATE TABLE membres (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE,
    adhesion_date DATE DEFAULT CURRENT_DATE 
);

-- 4. Table des Emprunts 
CREATE TABLE emprunts (
    id_emprunt SERIAL PRIMARY KEY,
    membre_id INTEGER REFERENCES membres(id) ON DELETE CASCADE,
    livre_id INTEGER REFERENCES livres(id) ON DELETE CASCADE,
    date_emprunt DATE NOT NULL,
    date_retour_prevue DATE NOT NULL,
    date_retour_effective DATE
);

3. Configuration du projet

    Modifiez la classe DatabaseConnection.java avec vos identifiants PostgreSQL :

        URL :"jdbc:postgresql://localhost:5432/gestion_bibliotheque"

        USER : votre_utilisateur

        PASSWORD : votre_mot_de_passe

🧪 Exécution des Tests

Pour vérifier le bon fonctionnement du système, lancez la classe Main.java. Vous pouvez utiliser les données de test intégrées pour valider :

    L'ajout d'un membre.
    
    rechercher un Livre
    
    inscrire un menbre
    
    Enregistrer un emprunt
    
    affciher les emprunts
    
    L'emprunt d'un livre existant.
    
    retourner un livre
    Le calcul de pénalité en simulant une date de retour dépassée.

📂 Contenu du Dépôt

    /src : Code source Java (Modèles, DAO, Ressources, Main).

    classes_UML.pdf : Diagramme de classes du projet.

    script_db.sql : Script de création de la base de données.

    lien_github.txt : URL du dépôt.
