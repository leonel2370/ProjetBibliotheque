package datastructures.dao;


import datastructures.databases.DBConnection;
import datastructures.model.Membre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MembreDAO {
    public void inscrireMembre(Membre membre) {
        String sql = "INSERT INTO membres (nom, prenom, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, membre.getNom());
            pstmt.setString(2, membre.getPrenom());
            pstmt.setString(3, membre.getEmail());
            pstmt.executeUpdate();
            System.out.println("Membre inscrit !");
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
