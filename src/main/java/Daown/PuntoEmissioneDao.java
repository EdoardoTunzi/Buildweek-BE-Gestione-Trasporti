package Daown;

import entities.PuntoDiEmissione;

import javax.persistence.EntityManager;

public class PuntoEmissioneDao {

    private EntityManager em;

    public PuntoEmissioneDao(EntityManager em) {
        this.em = em;
    }

    public void savePuntoEmissione(PuntoDiEmissione p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public PuntoDiEmissione getPuntoEmissioneById(long id) {
        return em.find(PuntoDiEmissione.class, id);
    }


    public void deletePuntoEmissione(PuntoDiEmissione p) {
        em.getTransaction().begin();
        // gli do l'istruzione che voglio eseguire- inm questo caso persist() per salvare su db
        //e gli passo nel parametro cosa rimuovere
        em.remove(p);
        //gli dico di eseguire con commit
        em.getTransaction().commit();
    }

}
