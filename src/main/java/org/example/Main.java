package org.example;


import com.github.javafaker.Faker;
import dao.TitoloDiViaggiDao;
import dao.PuntoEmissioneDao;
import dao.TesseraDao;
import dao.UtenteDao;
import entities.*;
import enumerated.TipoAbbonamento;
import enumerated.TipoBiglietto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aziendaTrasporti");
    private static EntityManager em = emf.createEntityManager();
    private static Faker faker = new Faker();

    public static void main(String[] args) {

        UtenteDao utenteDao = new UtenteDao(em);
        TesseraDao tesseraDao = new TesseraDao(em);
        TitoloDiViaggiDao titolodiviaggioDao = new TitoloDiViaggiDao(em);
        PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(em);
        //puntoEmissioneDao.savePuntoEmissione();


        Tessera t = new Tessera(faker.number().digits(8), LocalDate.now());
        //tesseraDao.saveTessera(t);

        Utente u = new Utente(faker.name().firstName(), faker.name().lastName(), tesseraDao.getTesseraById(4L), false);
        //utenteDao.saveUtente(u);

        PuntoDiEmissione ped = new DistributoreAutomatico(faker.company().name(), true);
        PuntoDiEmissione per = new RivenditoreAutorizzato(faker.company().name(), faker.address().fullAddress());
        //puntoEmissioneDao.savePuntoEmissione(per);
        //puntoEmissioneDao.savePuntoEmissione(ped);

        TitoloDiViaggio tvb = new Biglietto(19.90, LocalDate.of(2025, 1, 7), puntoEmissioneDao.getPuntoEmissioneById(1L), false, TipoBiglietto.ANDATA);
        TitoloDiViaggio tva = new Abbonamento(21.90, LocalDate.of(2024,12,6), puntoEmissioneDao.getPuntoEmissioneById(2L), tesseraDao.getTesseraById(2L), TipoAbbonamento.MENSILE);
       // titolodiviaggioDao.saveTitoloDiViaggio(tvb);
        //titolodiviaggioDao.saveTitoloDiViaggio(tva);


        // ricerca all titoli di viaggio
        //System.out.println(titolodiviaggioDao.ricercaListaTitoliViaggioEmessi());

        //ricerca per punto di emissione
        LocalDate dataInizio = LocalDate.of(2025, 1, 1);
        LocalDate dataFine = LocalDate.now();
        //System.out.println(titolodiviaggioDao.ricercaTitoliViaggioPerPuntoEmissione(puntoEmissioneDao.getPuntoEmissioneById(2L), dataInizio, dataFine));


        System.out.println(titolodiviaggioDao.verificaValiditàAbbonamentoTramiteNumeroTessera(tesseraDao.getTesseraById(2L)));


        em.close();
        emf.close();
    }
}
