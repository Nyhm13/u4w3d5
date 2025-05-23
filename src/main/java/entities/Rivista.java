package entities;

import enumeration.Periodicita;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "riviste")
public class Rivista extends Elemento{
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Rivista() {
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "ISBN='" + getIsbn() + '\'' +
                ", Titolo='" + getTitolo() + '\'' +
                ", Anno Pubblicazione=" + getAnnoPubblicazione() +
                ", Numero Pagine=" + getNumeroPagine() +
                ", Periodicità=" + periodicita +
                '}';
    }
}
