package datastructures.dao;

import datastructures.databases.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmpruntDAO {
    public void enregistrerEmprunt(int membreId, int livreId) {
        // Date actuelle et date de retour prévue (J+14)
        LocalDate aujourdhui = LocalDate.now();
        LocalDate retourPrevu = aujourdhui.plusDays(14);

        String sql = "INSERT INTO emprunts (membre_id, livre_id, date_emprunt, date_retour_prevue) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, membreId);
            pstmt.setInt(2, livreId);
            pstmt.setDate(3, java.sql.Date.valueOf(aujourdhui));
            pstmt.setDate(4, java.sql.Date.valueOf(retourPrevu));

            pstmt.executeUpdate();
            // Important : On devrait ici décrémenter le stock dans la table Livres
            System.out.println("Emprunt enregistré. À rendre avant le : " + retourPrevu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gererRetour(int empruntId) {
        LocalDate aujourdhui = LocalDate.now();
        String sqlSelect = "SELECT date_retour_prevue FROM emprunts WHERE id = ?";
        String sqlUpdate = "UPDATE emprunts SET date_retour_effective = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            // 1. Récupérer la date prévue pour calculer la pénalité
            PreparedStatement psSel = conn.prepareStatement(sqlSelect);
            psSel.setInt(1, empruntId);
            ResultSet rs = psSel.executeQuery();

            if (rs.next()) {
                LocalDate datePrevue = rs.getDate("date_retour_prevue").toLocalDate();

                // 2. Calculer la pénalité
                long joursRetard = ChronoUnit.DAYS.between(datePrevue, aujourdhui);
                if (joursRetard > 0) {
                    long penalite = joursRetard * 100;
                    System.out.println("RETARD ! Pénalité à payer : " + penalite + " F CFA");
                } else {
                    System.out.println("Livre rendu à temps. Merci !");
                }

                // 3. Mettre à jour la base de données
                PreparedStatement psUp = conn.prepareStatement(sqlUpdate);
                psUp.setDate(1, java.sql.Date.valueOf(aujourdhui));
                psUp.setInt(2, empruntId);
                psUp.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

