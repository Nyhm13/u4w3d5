package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "prestiti")
public class Prestito {

 @Id
 @GeneratedValue
 private long id;// mi creo un id per il prestito in quanto la traccia non lo presentava e mi serve per fare le relazioni
 @ManyToOne
 @JoinColumn(name = "utente_id")
 private Utente utente;
 @ManyToOne
 @JoinColumn(name = "elemento_prestato_id")
 private Elemento elementoPrestato;
 private LocalDate dataInizioPrestito;
 private LocalDate dataRestituzionePrevista;
 private LocalDate restituzioneEffettiva;

    public Prestito( LocalDate dataInizioPrestito, LocalDate dataRestituzionePrevista, LocalDate restituzioneEffettiva) {

        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    public Prestito() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Elemento getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Elemento elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getRestituzioneEffettiva() {
        return restituzioneEffettiva;
    }

    public void setRestituzioneEffettiva(LocalDate restituzioneEffettiva) {
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", restituzioneEffettiva=" + restituzioneEffettiva +
                '}';
    }
}
