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


        Tessera t = new Tessera("ex00021bv", LocalDate.now());
        //tesseraDao.saveTessera(t);

        Utente u = new Utente(faker.name().firstName(), faker.name().lastName(), tesseraDao.getTesseraById(2L), false);
        //utenteDao.saveUtente(u);

        PuntoDiEmissione pe = new DistributoreAutomatico("trixieET102", true);
        //puntoEmissioneDao.savePuntoEmissione(pe);

        TitoloDiViaggio tv = new Abbonamento(104.00, LocalDate.now(), puntoEmissioneDao.getPuntoEmissioneById(4L), tesseraDao.getTesseraById(2L), TipoAbbonamento.MENSILE);
        //titolodiviaggioDao.saveTitoloDiViaggio(tv);


        // ricerca all titoli di viaggio
        //System.out.println(titolodiviaggioDao.ricercaListaTitoliViaggioEmessi());

        //ricerca per punto di emissione
        LocalDate dataInizio = LocalDate.of(2025, 1, 20);
        LocalDate dataFine = LocalDate.now();
        //System.out.println(titolodiviaggioDao.ricercaTitoliViaggioPerPuntoEmissione(puntoEmissioneDao.getPuntoEmissioneById(4L), dataInizio, dataFine));


        //System.out.println(titolodiviaggioDao.verificaValiditàAbbonamentoTramiteNumeroTessera(tesseraDao.getTesseraById(1L)));


        em.close();
        emf.close();
    }
}
