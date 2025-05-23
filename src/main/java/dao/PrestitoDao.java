package dao;

import entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PrestitoDao {
    private EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    // 1# Aggiungere un prestito
    public void save(Prestito prestito) {
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }

    // 2# Ricercare un prestito per ID
    public Prestito findById(Long id) {
        return em.find(Prestito.class, id);
    }

    // 3# Rimuovere un prestito
    public void remove(Long id) {
        Prestito prestito = findById(id);

        if (prestito != null) {
            em.getTransaction().begin();
            em.remove(prestito);
            em.getTransaction().commit();
        } else {
            System.out.println("Prestito con ID " + id + " non trovato");
        }
    }

    // 4# Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
    public List<Prestito> findPrestitiAttiviByNumeroTessera(String numeroTessera) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroDiTessera = :numeroTessera AND p.restituzioneEffettiva IS NULL",
                Prestito.class
        );
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }

    // 5# Ricerca di tutti i prestiti scaduti e non ancora restituiti
    public List<Prestito> findPrestitiScadutiNonRestituiti() {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.restituzioneEffettiva IS NULL",
                Prestito.class
        );
        return query.getResultList();
    }
    //Fatto un metodo con una query per rimuovere i prestiti associati a un elemento specifico
    public void removeByElementoIsbn(String isbn) {
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM Prestito p WHERE p.elementoPrestato.isbn = :isbn")
                .setParameter("isbn", isbn)
                .executeUpdate();
        em.getTransaction().commit();

        if (deletedCount > 0) {
            System.out.println("Prestiti associati all'elemento con ISBN " + isbn + " rimossi.");
        } else {
            System.out.println("Nessun prestito trovato per l'elemento con ISBN " + isbn + ".");
        }
    }
}