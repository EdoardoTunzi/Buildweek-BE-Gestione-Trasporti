package dao;

import entities.Servizio;
import entities.Tratta;

import javax.persistence.EntityManager;

public class TrattaDAO {
    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void saveTratta(Tratta m) {
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }

    public Tratta getTrattaById(long id) {
        return em.find(Tratta.class, id);
    }


    public void deleteTratta(Tratta m) {
        em.getTransaction().begin();
        // gli do l'istruzione che voglio eseguire- inm questo caso persist() per salvare su db
        //e gli passo nel parametro cosa rimuovere
        em.remove(m);
        //gli dico di eseguire con commit
        em.getTransaction().commit();
    }
}
