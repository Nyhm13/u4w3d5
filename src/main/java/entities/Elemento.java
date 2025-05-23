package entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "elementi_catalogo")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Elemento {
    @Id
    @Column(name = "isbn", unique = true,nullable = false)
    private String isbn;
    private  String titolo;
    @Column(name = "anno_pubblicazione")
    private int annoPubblicazione;
    @Column(name = "numero_pagine")
    private int numeroPagine;
    @OneToMany(mappedBy ="elementoPrestato")
    private List<Prestito> prestiti;

    public Elemento(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }
    public Elemento() {
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    @Override
    public String toString() {
        return "Elemento{" +
                "isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }

    //Facciamo l`ovveride del metodo equals nella classe Elemento
    //per far si che le sue sottoclassi possano ereditare questo metodo equals
    // e far si che il confronto tra oggetti di tipo Elemento
    //sia fatto in base al valore isbn e non in base al riferimento della locazione in memoria
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Elemento elemento = (Elemento) o;
        return Objects.equals(isbn, elemento.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }
}
