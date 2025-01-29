package Daown;

import entities.Manutenzione;

import javax.persistence.EntityManager;

public class ManutenzioneDAO {
    private EntityManager em;

    public ManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public void saveManutenzione(Manutenzione m) {
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }

    public Manutenzione getManutenzioneById(long id) {
        return em.find(Manutenzione.class, id);
    }


    public void deleteManutenzione(Manutenzione m) {
        em.getTransaction().begin();
        // gli do l'istruzione che voglio eseguire- inm questo caso persist() per salvare su db
        //e gli passo nel parametro cosa rimuovere
        em.remove(m);
        //gli dico di eseguire con commit
        em.getTransaction().commit();
    }
}
