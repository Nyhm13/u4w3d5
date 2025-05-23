package entities;

import dao.ElementoDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import enumeration.Periodicita;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();

        try {
            // Utilizzo dei DAO
            ElementoDao elementoDao = archivio.getElementoDAO();
            UtenteDao utenteDao = archivio.getUtenteDao();
            PrestitoDao prestitoDao = archivio.getPrestitoDAO();

            // 1. Aggiungo elementi al catalogo
            //uso UUID per generare un ISBN unico e casuale poi il toString() lo converte in stringa
            Libro libro1 = new Libro(UUID.randomUUID().toString(), "Il Signore degli Anelli", 1954, 1200, "J.R.R. Tolkien", "Fantasy");
            Rivista rivista1 = new Rivista(UUID.randomUUID().toString(), "National Geographic", 2024, 150, Periodicita.MENSILE);
            Libro libro2 = new Libro(UUID.randomUUID().toString(), "Lo Hobbit", 1937, 300, "J.R.R. Tolkien", "Fantasy");
            Libro libro3 = new Libro(UUID.randomUUID().toString(), "cadomalato", 1937, 300, "ottaviano", "Fantasy");

            elementoDao.save(libro1);
            elementoDao.save(rivista1);
            elementoDao.save(libro2);
            System.out.println("Elementi aggiunti al catalogo.");

            // 2. Aggiungo  utenti
            Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1980, 5, 15), UUID.randomUUID().toString());
            Utente utente2 = new Utente("Laura", "Bianchi", LocalDate.of(1992, 10, 20), UUID.randomUUID().toString());

            utenteDao.save(utente1);
            utenteDao.save(utente2);
            System.out.println("Utenti aggiunti.");

            // 3. Registro i  prestiti
            Prestito prestito1 = new Prestito();
            prestito1.setUtente(utente1);
            prestito1.setElementoPrestato(libro1);
            prestito1.setDataInizioPrestito(LocalDate.now());
            prestito1.setDataRestituzionePrevista(LocalDate.now().plusDays(30));

            Prestito prestito2 = new Prestito();
            prestito2.setUtente(utente2);
            prestito2.setElementoPrestato(rivista1);
            prestito2.setDataInizioPrestito(LocalDate.now().minusDays(40));
            prestito2.setDataRestituzionePrevista(prestito2.getDataInizioPrestito().plusDays(30));

            prestitoDao.save(prestito1);
            prestitoDao.save(prestito2);
            System.out.println("Prestiti registrati.");

            // 4. Ricerca elemento per ISBN
            System.out.println("\n--- Ricerca elemento per ISBN ---");
            Elemento foundElemento = elementoDao.findByIsbn(libro1.getIsbn());
            if (foundElemento != null) {
                System.out.println("Trovato: " + foundElemento);
            } else {
                System.out.println("Elemento non trovato.");
            }

            // 5. Ricerca per anno di pubblicazione
            System.out.println("\n--- Ricerca per anno di pubblicazione (1954) ---");
            List<Elemento> elementiByAnno = elementoDao.findByAnnoPubblicazione(1954);
            if (!elementiByAnno.isEmpty()) {
                elementiByAnno.forEach(System.out::println);
            } else {
                System.out.println("Nessun elemento trovato per l'anno specificato.");
            }

            // 6. Ricerca per autore
            System.out.println("\n--- Ricerca per autore ('tolkien') ---");
            List<Libro> libriByAutore = elementoDao.findByAutore("tolkien");
            if (!libriByAutore.isEmpty()) {
                libriByAutore.forEach(System.out::println);
            } else {
                System.out.println("Nessun libro trovato per l'autore specificato.");
            }

            // 7. Ricerca per titolo
            System.out.println("\n--- Ricerca per titolo ('signore') ---");
            List<Elemento> elementiByTitolo = elementoDao.findByTitolo("signore");
            if (!elementiByTitolo.isEmpty()) {
                elementiByTitolo.forEach(System.out::println);
            } else {
                System.out.println("Nessun elemento trovato per il titolo specificato.");
            }

            // 8. Ricerca elementi in prestito per numero di tessera utente
            System.out.println("\n--- Elementi in prestito per utente 1 (tessera: " + utente1.getNumeroDiTessera() + ") ---");
            List<Prestito> prestitiAttiviUtente1 = prestitoDao.findPrestitiAttiviByNumeroTessera(utente1.getNumeroDiTessera());
            if (!prestitiAttiviUtente1.isEmpty()) {
                prestitiAttiviUtente1.forEach(p -> System.out.println("Elemento: " + p.getElementoPrestato().getTitolo()));
            } else {
                System.out.println("Nessun prestito attivo trovato per l'utente specificato.");
            }

            // 9. Ricerca di tutti i prestiti scaduti e non ancora restituiti
            System.out.println("\n--- Prestiti scaduti e non restituiti ---");
            List<Prestito> prestitiScaduti = prestitoDao.findPrestitiScadutiNonRestituiti();
            if (!prestitiScaduti.isEmpty()) {
                prestitiScaduti.forEach(p -> System.out.println("Elemento: " + p.getElementoPrestato().getTitolo() + ", Utente: " + p.getUtente().getNome() + " " + p.getUtente().getCognome() + ", Data Inizio: " + p.getDataInizioPrestito() + ", Data Prevista: " + p.getDataRestituzionePrevista()));
            } else {
                System.out.println("Nessun prestito scaduto trovato.");
            }
            System.out.println(prestito1.getElementoPrestato().getIsbn());
            // 10. Rimuovo un elemento
//            System.out.println("\n--- Rimozione Rivista (ISBN: " + rivista1.getIsbn() + ") ---");
//            prestitoDao.removeByElementoIsbn(rivista1.getIsbn());
//            elementoDao.remove(rivista1.getIsbn());

            // 11. Rimuovo un elemento
            System.out.println("\n--- Rimozione Libro (ISBN: " + libro3.getIsbn() + ") ---");
            elementoDao.remove(libro3.getIsbn());
            System.out.println("Elemento rimosso con successo!");



        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (archivio.getEm().isOpen()) {
                archivio.getEm().close();
            }
        }
    }
}
