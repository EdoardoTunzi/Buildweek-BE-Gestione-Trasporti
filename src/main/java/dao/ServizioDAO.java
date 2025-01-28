package dao;

import entities.Mezzo;
import entities.Servizio;

import javax.persistence.EntityManager;

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
}
