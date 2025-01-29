package org.example;


import com.github.javafaker.Faker;
import Daown.*;
import entities.*;
import enumerated.StatoMezzo;
import enumerated.TipoAbbonamento;
import enumerated.TipoBiglietto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aziendaTrasporti");
    private static EntityManager em = emf.createEntityManager();
    private static Faker faker = new Faker(new Locale("it"));

    public static void main(String[] args) {



        UtenteDao utenteDao = new UtenteDao(em);
        TesseraDao tesseraDao = new TesseraDao(em);
        TitoloDiViaggiDao titolodiviaggioDao = new TitoloDiViaggiDao(em);
        PuntoEmissioneDao puntoEmissioneDao = new PuntoEmissioneDao(em);
        MezzoDAO mezzoDao = new MezzoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        ServizioDAO servizioDAO = new ServizioDAO(em);
        ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(em);
        Scanner scanner = new Scanner(System.in);

        Tessera t = new Tessera(faker.number().digits(8), LocalDate.now());
        tesseraDao.saveTessera(t);

        Utente u = new Utente(faker.name().firstName(), faker.name().lastName(), tesseraDao.getTesseraById(4L), false);
        utenteDao.saveUtente(u);

        PuntoDiEmissione ped = new DistributoreAutomatico(faker.company().name(), true);
        PuntoDiEmissione per = new RivenditoreAutorizzato(faker.company().name(), faker.address().fullAddress());
//        puntoEmissioneDao.savePuntoEmissione(per);
//        puntoEmissioneDao.savePuntoEmissione(ped);

        TitoloDiViaggio tvb = new Biglietto(12.90, LocalDate.of(2025, 1, 1), puntoEmissioneDao.getPuntoEmissioneById(1L));
        TitoloDiViaggio tva = new Abbonamento(21.90, LocalDate.of(2025, 1, 1), puntoEmissioneDao.getPuntoEmissioneById(2L), tesseraDao.getTesseraById(1L), TipoAbbonamento.MENSILE);
//        titolodiviaggioDao.saveTitoloDiViaggio(tvb);
//        titolodiviaggioDao.saveTitoloDiViaggio(tva);

        Mezzo mezzo1 = new Autobus(40, StatoMezzo.IN_SERVIZIO);
        Mezzo mezzo2 = new Tram(100, StatoMezzo.IN_SERVIZIO);
        Mezzo mezzo3 = new Autobus(41, StatoMezzo.IN_MANUTENZIONE);
        Mezzo mezzo4 = new Tram(101, StatoMezzo.IN_MANUTENZIONE);
//        mezzoDao.saveMezzo(mezzo1);
//        mezzoDao.saveMezzo(mezzo2);
//        mezzoDao.saveMezzo(mezzo3);
//        mezzoDao.saveMezzo(mezzo4);

        Tratta trt1 = new Tratta("Termini", "Barberini", 30);
        Tratta trt2 = new Tratta("Termini", "Tor Vergata", 40);
        Tratta trt3 = new Tratta("Termini", "Anagnina", 50);
//        trattaDAO.saveTratta(trt1);
//        trattaDAO.saveTratta(trt2);
//        trattaDAO.saveTratta(trt3);

        Servizio serv1 = new Servizio(mezzoDao.getMezzoById(1), trattaDAO.getTrattaById(1), 35);
        Servizio serv2 = new Servizio(mezzoDao.getMezzoById(1), trattaDAO.getTrattaById(2), 45);
        Servizio serv3 = new Servizio(mezzoDao.getMezzoById(2), trattaDAO.getTrattaById(1), 55);
//        servizioDAO.saveServizio(serv1);
//        servizioDAO.saveServizio(serv2);
//        servizioDAO.saveServizio(serv3);

        Manutenzione man1 = new Manutenzione(mezzoDao.getMezzoById(3), LocalDate.now(), LocalDate.now().plusDays(2));
        Manutenzione man2 = new Manutenzione(mezzoDao.getMezzoById(4), LocalDate.now(), LocalDate.now().plusDays(3));
//        manutenzioneDAO.saveManutenzione(man1);
//        manutenzioneDAO.saveManutenzione(man2);



        //---------------------- Interfaccia Utente ---------------------
        boolean exit = false;



        while(!exit) {
            System.out.println("Benvenuto sul gestionale Epicode trasporti Roma! ");
            System.out.println("➡️Inserisci 1 se vuoi accedere come utente");
            System.out.println("➡️Inserisci 2 se vuoi accedere come amministratore");
            System.out.println("➡️Inserisci 0 per uscire dal programma");
            int user = Integer.parseInt(scanner.nextLine());
            switch (user) {
                case 0:
                    System.out.println("Sei uscito correttamente dal programma! Torna presto!");
                    exit= true;
                    break;
                case 1:

                    System.out.println("Benvenuto sul tuo profilo Utente");
                    System.out.println("Cosa vuoi fare?");
                    System.out.println("➡️Inserisci 1 se vuoi acquistare un biglietto");
                    System.out.println("➡️Inserisci 2 se vuoi acquistare un abbonamento");
                    System.out.println("➡️Inserisci 3 per timbrare il biglietto");

                    int sceltacase1 = Integer.parseInt(scanner.nextLine());
                    switch(sceltacase1){
                        case 1:
                            System.out.println("Hai scelto di acquistare un biglietto!");
                            System.out.println("Vuoi acquistare il biglietto in un distrubutore(1) o tramite un rivenditore(2)?");
                           int sceltaLuogo = Integer.parseInt(scanner.nextLine());
                            if(sceltaLuogo == 1){
                                System.out.println("Hai scelto di acquistare tramite un distrubutore Automatico!");
                                double prezzo= 12.50;
                                LocalDate dataEmissione = LocalDate.now();
                                PuntoDiEmissione puntoDiEmissione = puntoEmissioneDao.getPuntoEmissioneById(2);
                                TitoloDiViaggio bigliettoCreato = new Biglietto(prezzo, dataEmissione, puntoDiEmissione);
                                titolodiviaggioDao.saveTitoloDiViaggio(bigliettoCreato);
                                System.out.println("Biglietto acquistato correttamente!");
                                System.out.println("Codice biglietto:"+ bigliettoCreato.getId());

                            }else if(sceltaLuogo == 2){
                                System.out.println("Hai scelto di acquistare tramite un rivenditore!");
                                double prezzo= 12.50;
                                LocalDate dataEmissione = LocalDate.now();
                                PuntoDiEmissione puntoDiEmissione = puntoEmissioneDao.getPuntoEmissioneById(1);
                                TitoloDiViaggio bigliettoCreato = new Biglietto(prezzo, dataEmissione, puntoDiEmissione);
                                titolodiviaggioDao.saveTitoloDiViaggio(bigliettoCreato);
                                System.out.println("Biglietto acquistato correttamente!");
                                System.out.println("Codice biglietto:"+ bigliettoCreato.getId());
                            }else{
                                System.out.println("Operazione non valida!");
                            }


                            break;
                        case 2:
                            System.out.println("Hai scelto di acquistare un abbonamento!");
                            System.out.println("Per comprare un abbonamento inserire il numero tessera Utente");
                            String tessera = scanner.nextLine();
                        Tessera tesseraUtente = tesseraDao.getTesseraByNumeroTessera(tessera);

                            System.out.println("Vuoi acquistare l'abbonamento in un distrubutore(1) o tramite un rivenditore(2)?");
                            int sceltaLuogo2 = Integer.parseInt(scanner.nextLine());
                            if(sceltaLuogo2 == 1){
                                System.out.println("Hai scelto di acquistare tramite un distrubutore Automatico!");
                                double prezzo= 35.50;
                                LocalDate dataEmissione = LocalDate.now();
                                PuntoDiEmissione puntoDiEmissione = puntoEmissioneDao.getPuntoEmissioneById(2);
                                System.out.println("Digita 1 per inserire un abbonamento MENSILE o 2 per inserire un abbonamento SETTIMANALE");
                                int sceltaAbbonamento = Integer.parseInt(scanner.nextLine());
                                if(sceltaAbbonamento == 1){
                                    System.out.println("Hai scelto di acquistare un abbonamento MENSILE");
                                    TitoloDiViaggio abbonamentoCreato = new Abbonamento(prezzo, dataEmissione, puntoDiEmissione,tesseraUtente,TipoAbbonamento.MENSILE);
                                    titolodiviaggioDao.saveTitoloDiViaggio(abbonamentoCreato);
                                    System.out.println("Abbonamento acquistato correttamente!");
                                    System.out.println("Codice abbonamento:"+ abbonamentoCreato.getId());
                                } else if (sceltaAbbonamento == 2) {
                                    System.out.println("Hai scelto di acquistare un abbonamento SETTIMANALE");
                                    TitoloDiViaggio abbonamentoCreato = new Abbonamento(prezzo, dataEmissione, puntoDiEmissione,tesseraUtente,TipoAbbonamento.SETTIMANALE);
                                    titolodiviaggioDao.saveTitoloDiViaggio(abbonamentoCreato);
                                    System.out.println("Abbonamento acquistato correttamente!");
                                    System.out.println("Codice abbonamento:"+ abbonamentoCreato.getId());

                                }
                            }else if(sceltaLuogo2 == 2){
                                System.out.println("Hai scelto di acquistare tramite un rivenditore!");
                                double prezzo= 35.50;
                                LocalDate dataEmissione = LocalDate.now();
                                PuntoDiEmissione puntoDiEmissione = puntoEmissioneDao.getPuntoEmissioneById(2);
                                System.out.println("Digita 1 per inserire un abbonamento MENSILE o 2 per inserire un abbonamento SETTIMANALE");
                                int sceltaAbbonamento = Integer.parseInt(scanner.nextLine());
                                if(sceltaAbbonamento == 1){
                                    System.out.println("Hai scelto di acquistare un abbonamento MENSILE");
                                    TitoloDiViaggio abbonamentoCreato = new Abbonamento(prezzo, dataEmissione, puntoDiEmissione,tesseraUtente,TipoAbbonamento.MENSILE);
                                    titolodiviaggioDao.saveTitoloDiViaggio(abbonamentoCreato);
                                    System.out.println("Abbonamento acquistato correttamente!");
                                    System.out.println("Codice abbonamento:"+ abbonamentoCreato.getId());
                                } else if (sceltaAbbonamento == 2) {
                                    System.out.println("Hai scelto di acquistare un abbonamento SETTIMANALE");
                                    TitoloDiViaggio abbonamentoCreato = new Abbonamento(prezzo, dataEmissione, puntoDiEmissione,tesseraUtente,TipoAbbonamento.SETTIMANALE);
                                    titolodiviaggioDao.saveTitoloDiViaggio(abbonamentoCreato);
                                    System.out.println("Abbonamento acquistato correttamente!");
                                    System.out.println("Codice abbonamento:"+ abbonamentoCreato.getId());
                                }

                            }else{
                                System.out.println("Operazione non valida!");
                            }

                            break;
                        case 3:
                            System.out.println("Hai scelto di timbrare il biglietto!");
                            System.out.println("Inserisci il numero del biglietto");
                            Long nBiglietto = scanner.nextLong();
                            scanner.nextLine();
                            Biglietto bigliettoUtente = titolodiviaggioDao.getBigliettoById(nBiglietto);
                            System.out.println("Seleziona la linea desiderata(1-3)");
                            Long nLinea = scanner.nextLong();
                            scanner.nextLine();
                            if(nLinea==1){
                                System.out.println("Hai scelto la prima linea come servizio");
                                titolodiviaggioDao.vidimaBiglietto(bigliettoUtente,servizioDAO.getServizioById(nLinea));
                            } else if (nLinea==2) {
                                System.out.println("Hai scelto la seconda linea come servizio");
                                titolodiviaggioDao.vidimaBiglietto(bigliettoUtente,servizioDAO.getServizioById(nLinea));
                            }else if (nLinea==3) {
                                System.out.println("Hai scelto la terza linea come servizio");
                                titolodiviaggioDao.vidimaBiglietto(bigliettoUtente,servizioDAO.getServizioById(nLinea));
                            }else{
                                System.out.println("Opzione non valida!");
                            }
                            break;
                    }

                    break;
                case 2:
                    System.out.println("Inserisci il tuo ID amministratore");
                    System.out.println("Benvenuto sul tuo profilo Amministratore");

                    //SEZIONE AGGIUNTA E RIMOZIONE SWITCH 1
                    //Aggiungere/Eliminare abbonamenti
                    //Aggiungere/Eliminare utenti
                    //Aggiungere/Eliminare Tessere
                    //Aggiungere/Eliminare tratte
                    //Aggiungere/Eliminare Mezzi


                    //GESTIONE VISUALIZZAZIONE SWITCH 2
                    //Visualizzare numero biglietti/abbonamenti emessi in tot periodo
                    //Visualizzare periodi servizio/manutenzione
                    //Visualizzare biglietti vidimati su un singolo mezzo
                    //Visualizzare biglietti vidimati per data


                    //GESTIONE PERCORRENZE SWITCH 3
                    //Gestire percorrenze
                    //Tempo medio effettivo per tratta
                    //Numero di volte che un mezzo percorre una tratta


                    break;
                default:
                    System.out.println("Operazione non valida");
                    break;
            }
        }

        //---------------------- metodi dao ---------------------

        // RICERCA DI TUTTI I TITOLI DI VIAGGIO
        //System.out.println(titolodiviaggioDao.ricercaListaTitoliViaggioEmessi());

        //RICERCA TITOLI DI VIAGGIO PER PUNTO DI EMISSIONE
       /* LocalDate dataInizio = LocalDate.of(2025, 1, 1);
        LocalDate dataFine = LocalDate.now();
        System.out.println(titolodiviaggioDao.ricercaTitoliViaggioPerPuntoEmissione(puntoEmissioneDao.getPuntoEmissioneById(2L), dataInizio, dataFine));*/

        //VERIFICA ABBONAMENTO VALIDO DA NUMERO TESSERA
        //System.out.println(titolodiviaggioDao.verificaValiditàAbbonamentoTramiteNumeroTessera(tesseraDao.getTesseraById(2L)));

        //METODO PER VIDIMARE BIGLIETTO
        //titolodiviaggioDao.vidimaBiglietto(titolodiviaggioDao.getBigliettoById(9L), servizioDAO.getServizioById(1));

        //RICERCA BIGLIETTI VIDIMATI IN UN PERIODO DI TEMPO
       /* List<TitoloDiViaggio> lista = titolodiviaggioDao.getBigliettiVidimatiPerDate(LocalDate.of(2024, 12, 1), LocalDate.now());
        lista.forEach(System.out::println);*/

        //RICERCA NUMERO DI TRATTE PER MEZZO
        /*Long numeroTratte = servizioDAO.getNumeroTrattePerMezzo(mezzoDao.getMezzoById(1), trattaDAO.getTrattaById(1));
        System.out.println("Numero di tratte percorse: " + numeroTratte);*/

        // Metodo ADMIN per calcolo media tempo di percorrenza di una tratta di un mezzo
       /* Double mediaTratte = servizioDAO.getTempoMedioEffettivoPerTratta(mezzoDao.getMezzoById(1), trattaDAO.getTrattaById(1));
        System.out.println("Il tempo medio di percorrenza delle tratte richieste è: " + mediaTratte + " minuti.");*/

        // Metodo per cerca tempo effettivo di percorrenza di una tratta /mezzo
       /* List<Servizio> listaServiziTempiEffettivi = servizioDAO.getListaTratteETempiEffettiviPerMezzo(mezzoDao.getMezzoById(1), trattaDAO.getTrattaById(1));
        for (Servizio servizio : listaServiziTempiEffettivi) {
            System.out.println("Tempo Effettivo: " + servizio.getTempoEffettivo());
            }*/



        em.close();
        emf.close();
    }
}
