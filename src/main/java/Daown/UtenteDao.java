package Daown;

import entities.Utente;

import javax.persistence.EntityManager;

public class UtenteDao {

    private EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    public void saveUtente(Utente u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    public Utente getUtenteById(long id) {
        return em.find(Utente.class, id);
    }


    public void deleteUtente(Utente u) {
        em.getTransaction().begin();
        // gli do l'istruzione che voglio eseguire- inm questo caso persist() per salvare su db
        //e gli passo nel parametro cosa rimuovere
        em.remove(u);
        //gli dico di eseguire con commit
        em.getTransaction().commit();
    }

}
