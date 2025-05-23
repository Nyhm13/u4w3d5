package dao;

import entities.Utente;
import jakarta.persistence.*;

public class UtenteDao {
    private EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }


    // 1# Aggiungere un utente
    public void save(Utente utente) {
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    // 2# Ricercare un utente per ID
    public Utente findById(Long id) {
        return em.find(Utente.class, id);
    }

    // 3# Rimuovere un utente
    public void remove(Long id) {
        Utente utente = findById(id);

        if (utente != null) {
            em.getTransaction().begin();
            em.remove(utente);
            em.getTransaction().commit();
        } else {
            System.out.println("Utente con ID " + id + " non trovato");
        }
    }

    public Utente findByNumeroTessera(String numeroTessera) {
        try {
            TypedQuery<Utente> query = em.createQuery("SELECT u FROM Utente u WHERE u.numeroTessera = :numeroTessera", Utente.class);
            query.setParameter("numeroTessera", numeroTessera);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
