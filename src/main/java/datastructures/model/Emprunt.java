package datastructures.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprunt {
    private int idEmprunt, membreId, livreId;
    private LocalDate dateEmprunt, dateRetourPrevue, dateRetourEffective;

    public Emprunt(int id, int mId, int lId, LocalDate debut, LocalDate prevue) {
        this.idEmprunt = id;
        this.membreId = mId;
        this.livreId = lId;
        this.dateEmprunt = debut;
        this.dateRetourPrevue = prevue;
    }

    // Calcul de la pénalité
    public long calculerPenalite(LocalDate dateRetourReelle) {
        if (dateRetourReelle.isAfter(dateRetourPrevue)) {
            long joursRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourReelle);
            return joursRetard * 100; // 100 F CFA par jour [cite: 192]
        }
        return 0;
    }
}
