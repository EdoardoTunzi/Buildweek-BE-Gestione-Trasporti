package Daown;

import entities.Tessera;

import javax.persistence.EntityManager;

public class TesseraDao {

    private EntityManager em;

    public TesseraDao(EntityManager em) {
        this.em = em;
    }

    public void saveTessera(Tessera t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    public Tessera getTesseraById(long id) {
        return em.find(Tessera.class, id);
    }


    public void deleteTessera(Tessera t) {
        em.getTransaction().begin();
        // gli do l'istruzione che voglio eseguire- inm questo caso persist() per salvare su db
        //e gli passo nel parametro cosa rimuovere
        em.remove(t);
        //gli dico di eseguire con commit
        em.getTransaction().commit();
    }

}
