package dao;

import entities.Mezzo;
import entities.Utente;

import javax.persistence.EntityManager;

public class MezzoDAO {
    private EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void saveMezzo(Mezzo m) {
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }

    public Mezzo getMezzoById(long id) {
        return em.find(Mezzo.class, id);
    }


    public void deleteMezzo(Mezzo m) {
        em.getTransaction().begin();
        // gli do l'istruzione che voglio eseguire- inm questo caso persist() per salvare su db
        //e gli passo nel parametro cosa rimuovere
        em.remove(m);
        //gli dico di eseguire con commit
        em.getTransaction().commit();
    }
}
