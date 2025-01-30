package Dao;

import entities.Mezzo;
import entities.Servizio;
import entities.Tratta;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServizioDAO {

    private EntityManager em;

    public ServizioDAO(EntityManager em) {
        this.em = em;
    }

    public void saveServizio(Servizio m) {
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }

    public Servizio getServizioById(long id) {
        return em.find(Servizio.class, id);
    }


    public void deleteServizio(Servizio m) {
        em.getTransaction().begin();
        // gli do l'istruzione che voglio eseguire- inm questo caso persist() per salvare su db
        //e gli passo nel parametro cosa rimuovere
        em.remove(m);
        //gli dico di eseguire con commit
        em.getTransaction().commit();
    }

    public Long getNumeroTrattePerMezzo(Mezzo mezzo, Tratta tratta) {
        Query q = em.createQuery("SELECT COUNT(s) FROM Servizio s WHERE s.mezzo = :mezzo AND s.tratta = :tratta");
        q.setParameter("mezzo", mezzo);
        q.setParameter("tratta", tratta);
        return (Long) q.getSingleResult();
    }

    public List<Servizio> getListaTratteETempiEffettiviPerMezzo(Mezzo mezzo, Tratta tratta){
        Query q = em.createQuery("SELECT s FROM Servizio s WHERE s.mezzo = :mezzo AND s.tratta = :tratta", Servizio.class);
        q.setParameter("mezzo", mezzo);
        q.setParameter("tratta", tratta);
        return q.getResultList();
    }

    // Metodo ADMIN per calcolo media tempo di percorrenza di una tratta di un mezzo
    public Double getTempoMedioEffettivoPerTratta(Mezzo mezzo, Tratta tratta) {
        Query q = em.createQuery("SELECT AVG(s.tempoEffettivo) FROM Servizio s  WHERE s.mezzo = :mezzo AND s.tratta = :tratta");
        q.setParameter("mezzo", mezzo);
        q.setParameter("tratta", tratta);
        return (Double) q.getSingleResult();
    }
}
