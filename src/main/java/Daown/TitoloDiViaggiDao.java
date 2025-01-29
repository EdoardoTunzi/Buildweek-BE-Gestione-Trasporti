package Daown;

import com.github.javafaker.Faker;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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

    public Biglietto getBigliettoById(Long id) {
        return em.find(Biglietto.class, id);
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

   /* public void vidimaBiglietto (long id) {
        Biglietto b = em.find(Biglietto.class, id);
      *//* Query q = em.createQuery("SELECT b FROM Biglietto b WHERE b.id = :id ", Biglietto.class);*//*

        b.setVidimato(true);
        b.setValid(false);
       em.getTransaction().begin();
       em.
        *//*System.out.println("Biglietto n°: " + biglietto.getId() + " vidimato! Non valido per altre tratte");*//*
    }*/

    public void vidimaBiglietto(Biglietto biglietto, Servizio servizio) {

        if (!biglietto.getVidimato()) {
            em.getTransaction().begin();
            biglietto.setVidimato(true);
            biglietto.setValid(false);
            biglietto.setDataVidimazione(LocalDate.now());
            servizio.getBigliettiVidimati().add(biglietto);

            em.merge(biglietto);
            em.merge(servizio);
            em.getTransaction().commit();
        } else {
            System.out.println("Il biglietto che hai utilizzato non è valido perchè è già stato vidimato!");
        }
    }

    public List<TitoloDiViaggio> getBigliettiVidimatiSuUnMezzo(Mezzo mezzo) {
        Query q = em.createQuery("SELECT b FROM Servizio s JOIN s.bigliettiVidimati b WHERE s.mezzo = :mezzo AND b.isVidimato = true ", Biglietto.class);
        q.setParameter("mezzo", mezzo);
        return q.getResultList();
    }

    public List<TitoloDiViaggio> getBigliettiVidimatiPerDate(LocalDate dataInizio, LocalDate dataFine) {
        Query q = em.createQuery("SELECT b FROM Servizio s JOIN s.bigliettiVidimati b WHERE b.dataVidimazione BETWEEN :dataInizio AND :dataFine", Biglietto.class);
        q.setParameter("dataInizio", dataInizio);
        q.setParameter("dataFine", dataFine);
        return q.getResultList();
    }
}
