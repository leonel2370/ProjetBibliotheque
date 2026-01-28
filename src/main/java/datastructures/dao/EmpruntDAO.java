package datastructures.dao;

import datastructures.databases.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmpruntDAO {
    public void enregistrerEmprunt(String nomMembre, String titreLivre) {
        LocalDate aujourdhui = LocalDate.now();
        LocalDate retourPrevu = aujourdhui.plusDays(14);

        // Utilisation de ILIKE pour la recherche insensible à la casse
        // On garde "LIMIT 1" pour s'assurer de ne prendre qu'un seul résultat s'il y a plusieurs matchs
        String sqlGetMembre = "SELECT id FROM membres WHERE nom ILIKE ? LIMIT 1";
        String sqlGetLivre = "SELECT id FROM livres WHERE titre ILIKE ? LIMIT 1";

        String sqlEmprunt = "INSERT INTO emprunts (membre_id, livre_id, date_emprunt, date_retour_prevue) VALUES (?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE livres SET nombre_exemplaires = nombre_exemplaires - 1 WHERE id = ? AND nombre_exemplaires > 0";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // 1. Récupérer l'ID du membre (Recherche souple)
                int membreId = -1;
                try (PreparedStatement psM = conn.prepareStatement(sqlGetMembre)) {
                    psM.setString(1, "%" + nomMembre + "%"); // Permet de trouver "Jean" si on tape "jea"
                    ResultSet rs = psM.executeQuery();
                    if (rs.next()) membreId = rs.getInt("id");
                    else throw new SQLException("Membre non trouvé pour : " + nomMembre);
                }

                // 2. Récupérer l'ID du livre (Recherche souple comme dans LivreDAO)
                int livreId = -1;
                try (PreparedStatement psL = conn.prepareStatement(sqlGetLivre)) {
                    psL.setString(1, "%" + titreLivre + "%");
                    ResultSet rs = psL.executeQuery();
                    if (rs.next()) livreId = rs.getInt("id");
                    else throw new SQLException("Livre non trouvé pour : " + titreLivre);
                }

                // 3. Enregistrer l'emprunt
                try (PreparedStatement pstmt1 = conn.prepareStatement(sqlEmprunt)) {
                    pstmt1.setInt(1, membreId);
                    pstmt1.setInt(2, livreId);
                    pstmt1.setDate(3, java.sql.Date.valueOf(aujourdhui));
                    pstmt1.setDate(4, java.sql.Date.valueOf(retourPrevu));
                    pstmt1.executeUpdate();
                }

                // 4. Mettre à jour le stock
                try (PreparedStatement pstmt2 = conn.prepareStatement(sqlUpdateStock)) {
                    pstmt2.setInt(1, livreId);
                    int rowsAffected = pstmt2.executeUpdate();
                    if (rowsAffected == 0) throw new SQLException("Plus d'exemplaires disponibles !");
                }

                conn.commit();
                System.out.println("Emprunt enregistré avec succès !");

            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Erreur transactionnelle : " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherEmpruntsEnRetard() {
        String sql = "SELECT  m.nom, l.titre, e.date_retour_prevue " +
                "FROM emprunts e " +
                "JOIN membres m ON e.membre_id = m.id " +
                "JOIN livres l ON e.livre_id = l.id " +
                "WHERE e.date_retour_effective IS NULL AND e.date_retour_prevue < ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- LISTE DES RETARDS ---");
            while (rs.next()) {
                long retard = ChronoUnit.DAYS.between(rs.getDate("date_retour_prevue").toLocalDate(), LocalDate.now());
                System.out.println("Membre: " + rs.getString("nom") +
                        " | Livre: " + rs.getString("titre") + " | Retard: " + retard + " jours");
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}

