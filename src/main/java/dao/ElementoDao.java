package dao;

import entities.Elemento;
import entities.Libro;
import jakarta.persistence.EntityManager;

import jakarta.persistence.TypedQuery;

import java.util.List;

public class ElementoDao {
    private EntityManager em;

    public ElementoDao(EntityManager em) {
        this.em = em;
    }
//1# aggiungere un elemento al catalogo
    public void save(Elemento elemento) {
        em.getTransaction().begin();
        em.persist(elemento);
        em.getTransaction().commit();
    }
//3# ricercare un elemento per ISBN
    public Elemento findByIsbn(String isbn) {
        return em.find(Elemento.class, isbn);
    }
//2# rimuovere un elemento dal catalogo
public void remove(String isbn) {
    Elemento elemento = findByIsbn(isbn);

    if (elemento != null) {
        em.getTransaction().begin();

        // Rimuovi i prestiti associati all'elemento
        em.createQuery("DELETE FROM Prestito p WHERE p.elementoPrestato.isbn = :isbn")
                .setParameter("isbn", isbn)
                .executeUpdate();

        // Rimuovi l'elemento
        em.remove(elemento);

        em.getTransaction().commit();
    } else {
        System.out.println("Elemento con ISBN " + isbn + " non trovato");
    }
}
    //4# ricerca per anno di pubblicazione
    public List<Elemento> findByAnnoPubblicazione(int anno){
        TypedQuery<Elemento> query= em.createQuery("select e from Elemento e where e.annoPubblicazione=:anno",Elemento.class);
        query.setParameter("anno",anno);
        return query.getResultList();
    }
  //5# ricerca per autore
    //uso List<Libro> perche solo i libri hanno l`autore
    public List<Libro> findByAutore(String autore){
        // qui come per la ricerca del titolo o in parte del titolo uso lower per rendere la ricerca case insensitive quindi mi converte
        // tutto in minuscolo e lo confronta con il parametro passato
        //mentre il % serve per fare una ricerca parziale se per esempio cercassi tolkien ma nel db ho salvato J.R.R. Tolkien me lo troverebbe
        TypedQuery<Libro> query= em.createQuery("select l from Libro l where lower(l.autore) like lower(:autore)",Libro.class);
        query.setParameter("autore","%"+autore+"%");
        return  query.getResultList();// avrei potuto usare anche query.getSingleResult() se so che c`e solo un risultato
    }
    //6# ricerca per titolo o parte del titolo
    public List<Elemento> findByTitolo(String titolo){
        TypedQuery<Elemento> query=em.createQuery("select e from Elemento e where lower(e.titolo) like lower(:title)", Elemento.class);
        query.setParameter("title","%"+titolo+"%");
        return query.getResultList();
    }


}
