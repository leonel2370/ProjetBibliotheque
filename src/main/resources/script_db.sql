-- Insertion de Livres
INSERT INTO livres (titre, auteur, categorie, nombre_exemplaires) VALUES
('L''Étranger', 'Albert Camus', 'Roman', 5),
('Une si longue lettre', 'Mariama Bâ', 'Littérature Africaine', 3),
('Le Petit Prince', 'Antoine de Saint-Exupéry', 'Conte', 10),
('Clean Code', 'Robert C. Martin', 'Informatique', 2),
('L''Enfant Noir', 'Camara Laye', 'Littérature Africaine', 4);

-- Insertion de Membres
INSERT INTO membres (nom, prenom, email, adhesion_date) VALUES
('Diallo', 'Mamadou', 'mamadou.diallo@email.com', '2025-01-10'),
('Traoré', 'Fatou', 'fatou.traore@email.com', '2025-02-15'),
('Koffi', 'Jean', 'jean.koffi@email.com', '2025-03-01');

-- Insertion d'Emprunts (pour tester les retards)
-- Un emprunt déjà rendu (sans pénalité)
INSERT INTO emprunts (membre_id, livre_id, date_emprunt, date_retour_prevue, date_retour_effective)
VALUES (1, 1, '2025-11-01', '2025-11-15', '2025-11-14');

-- Un emprunt en retard (non encore rendu)
INSERT INTO emprunts (membre_id, livre_id, date_emprunt, date_retour_prevue, date_retour_effective)
VALUES (2, 4, '2025-12-01', '2025-12-15', NULL);