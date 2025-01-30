package Dao;

import entities.Tessera;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;

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

    public Tessera getTesseraByNumeroTessera(String numeroTessera) {
        Query q = em.createQuery("SELECT t FROM Tessera t WHERE t.numeroTessera = :numeroTessera", Tessera.class);
        q.setParameter("numeroTessera",numeroTessera );
        return (Tessera) q.getSingleResult();
    }

    public void deleteTessera(Tessera t) {
        em.getTransaction().begin();
        // gli do l'istruzione che voglio eseguire- inm questo caso persist() per salvare su db
        //e gli passo nel parametro cosa rimuovere
        em.remove(t);
        //gli dico di eseguire con commit
        em.getTransaction().commit();
    }
    public void rinnovoTessera(Tessera tessera){
        if(tessera.getDataScadenza().isBefore(LocalDate.now())){
            em.getTransaction().begin();
            tessera.setDataScadenza(LocalDate.now().plusYears(1));
            em.merge(tessera);
            em.getTransaction().commit();
            System.out.println("La tessera è stata rinnovata con successo!");
        }else{
            System.out.println("La tessera da rinnovare non è ancora scaduta!");
        }
    }

}
