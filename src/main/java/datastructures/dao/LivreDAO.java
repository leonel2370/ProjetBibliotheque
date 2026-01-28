package datastructures.dao;

import datastructures.databases.DBConnection;
import datastructures.model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
    public class LivreDAO {

        public void ajouterLivre(Livre livre) {
            String sql = "INSERT INTO livres (titre, auteur, categorie, nombre_exemplaires) VALUES (?, ?, ?, ?)";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, livre.getTitre());
                pstmt.setString(2, livre.getAuteur());
                pstmt.setString(3, livre.getCategorie());
                pstmt.setInt(4, livre.getNbExemplaires());
                pstmt.executeUpdate();
                System.out.println("Livre ajouté avec succès !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'ajout : " + e.getMessage());
            }
        }

        public List<Livre> rechercherParTitre(String titre) {
            List<Livre> resultats = new ArrayList<>();
            String sql = "SELECT * FROM livres WHERE titre ILIKE ?"; // ILIKE pour insensible à la casse
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, "%" + titre + "%");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    resultats.add(new Livre(rs.getInt("id"), rs.getString("titre"),
                            rs.getString("auteur"), rs.getString("categorie"),
                            rs.getInt("nb_exemplaires")));
                }
            } catch (SQLException e) { e.printStackTrace(); }
            return resultats;
        }
    }
