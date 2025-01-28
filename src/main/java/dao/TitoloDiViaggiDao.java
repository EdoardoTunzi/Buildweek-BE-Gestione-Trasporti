package dao;

import com.github.javafaker.Faker;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

public class TitoloDiViaggiDao {

    private Faker faker;
    private EntityManager em;

    public TitoloDiViaggiDao(EntityManager em) {
        this.em = em;
    }

    public void saveTitoloDiViaggio(TitoloDiViaggio tv) {
        em.getTransaction().begin();
        em.persist(tv);
        em.getTransaction().commit();
    }

    public TitoloDiViaggio getTitoloDiViaggioById(long id) {
        return em.find(TitoloDiViaggio.class, id);
    }


    public void deleteTitoloDiViaggio(TitoloDiViaggio tv) {
        em.getTransaction().begin();
        // gli do l'istruzione che voglio eseguire- inm questo caso persist() per salvare su db
        //e gli passo nel parametro cosa rimuovere
        em.remove(tv);
        //gli dico di eseguire con commit
        em.getTransaction().commit();
    }

    public List<TitoloDiViaggio> ricercaListaTitoliViaggioEmessi() {
        return em.createQuery("SELECT tv FROM TitoloDiViaggio tv", TitoloDiViaggio.class).getResultList();
    }


    public List<TitoloDiViaggio> ricercaTitoliViaggioPerPuntoEmissione(PuntoDiEmissione pe, LocalDate dataInizio, LocalDate dataFine) {
        return em.createQuery("SELECT tv FROM TitoloDiViaggio tv WHERE tv.puntoDiEmissione = :pe AND tv.dataEmissione BETWEEN  :dataInizio AND :dataFine", TitoloDiViaggio.class)
                .setParameter("pe", pe)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();
    }

    public TitoloDiViaggio verificaValiditàAbbonamentoTramiteNumeroTessera(Tessera tesseraUtente) {
        LocalDate oggi = LocalDate.now();
        try {
            return em.createQuery("SELECT tv FROM TitoloDiViaggio tv WHERE tv.tesseraUtente = :tesseraUtente " +
                                    "AND tv.dataFineValidità >= :oggi"
                            , TitoloDiViaggio.class)
                    .setParameter("tesseraUtente", tesseraUtente)
                    .setParameter("oggi", oggi)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("nessun abbonamento valido associato alla tessera " + tesseraUtente);
            return null;
        }
    }

    public void vidimaBiglietto (Biglietto biglietto) {
        biglietto.setVidimato(true);
        biglietto.setValid(false);
        System.out.println("Biglietto n°: " + biglietto.getId() + " vidimato! Non valido per altre tratte");
    }





}
