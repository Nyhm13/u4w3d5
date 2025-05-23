package entities;

import dao.ElementoDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Archivio {
    private EntityManager em;
    private ElementoDao elementoDAO;
    private PrestitoDao prestitoDAO;
    private UtenteDao utenteDao;

    public Archivio() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
        elementoDAO = new ElementoDao(em);
        prestitoDAO = new PrestitoDao(em);
        this.utenteDao = new UtenteDao(em);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ElementoDao getElementoDAO() {
        return elementoDAO;
    }

    public void setElementoDAO(ElementoDao elementoDAO) {
        this.elementoDAO = elementoDAO;
    }

    public PrestitoDao getPrestitoDAO() {
        return prestitoDAO;
    }

    public void setPrestitoDAO(PrestitoDao prestitoDAO) {
        this.prestitoDAO = prestitoDAO;
    }

    public UtenteDao getUtenteDao() {
        return utenteDao;
    }

    public void setUtenteDao(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
    }



    @Override
    public String toString() {
        return "Archivio{" +
                "em=" + em +
                ", elementoDAO=" + elementoDAO +
                ", prestitoDAO=" + prestitoDAO +
                ", utenteDao=" + utenteDao +
                '}';
    }
}
