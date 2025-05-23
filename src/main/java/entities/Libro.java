package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "libri")
public class Libro extends Elemento{

    private String autore;
    private String genere;

    public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro() {
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "ISBN='" + getIsbn() + '\'' +
                ", Titolo='" + getTitolo() + '\'' +
                ", Anno Pubblicazione=" + getAnnoPubblicazione() +
                ", Numero Pagine=" + getNumeroPagine() +
                ", Autore='" + autore + '\'' +
                ", Genere='" + genere + '\'' +
                '}';
    }
}
