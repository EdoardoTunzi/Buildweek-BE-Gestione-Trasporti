package org.example;


import com.github.javafaker.Faker;
import Dao.*;
import entities.*;
import enumerated.StatoMezzo;
import enumerated.TipoAbbonamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
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
//        tesseraDao.saveTessera(t);

        Utente u = new Utente(faker.name().firstName(), faker.name().lastName(), tesseraDao.getTesseraById(4L), false);
//        utenteDao.saveUtente(u);

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
                try {
                    System.out.println("Benvenuto sul gestionale Epicode trasporti Roma! ");
                    System.out.println("➡️Inserisci 1 se vuoi accedere come utente");
                    System.out.println("➡️Inserisci 2 se vuoi accedere come amministratore");
                    System.out.println("➡️Inserisci 0 per uscire dal programma");

                    int user = Integer.parseInt(scanner.nextLine());
                    switch (user) {
                        case 0:
                            System.out.println("Sei uscito correttamente dal programma! Torna presto!");
                            exit = true;
                            break;
                        case 1:

                            System.out.println("Benvenuto sul tuo profilo Utente");
                            System.out.println("Cosa vuoi fare?");
                            System.out.println("➡️ Inserisci 1 per acquistare un biglietto , 2 per acquistare un abbonamento, 3 per vidimare il biglietto");

                            int sceltacase1 = Integer.parseInt(scanner.nextLine());
                            switch (sceltacase1) {
                                case 1:
                                    System.out.println("Hai scelto di acquistare un biglietto!");
                                    System.out.println("Vuoi acquistare il biglietto in un distrubutore(1) o tramite un rivenditore(2)?");
                                    int sceltaLuogo = Integer.parseInt(scanner.nextLine());
                                    if (sceltaLuogo == 1) {
                                        System.out.println("Hai scelto di acquistare tramite un distrubutore Automatico!");
                                        double prezzo = 12.50;
                                        LocalDate dataEmissione = LocalDate.now();
                                        PuntoDiEmissione puntoDiEmissione = puntoEmissioneDao.getPuntoEmissioneById(2);
                                        TitoloDiViaggio bigliettoCreato = new Biglietto(prezzo, dataEmissione, puntoDiEmissione);
                                        titolodiviaggioDao.saveTitoloDiViaggio(bigliettoCreato);
                                        System.out.println("Ecco a te il biglietto numero: " + bigliettoCreato.getId());

                                    } else if (sceltaLuogo == 2) {
                                        System.out.println("Hai scelto di acquistare tramite un rivenditore!");
                                        double prezzo = 12.50;
                                        LocalDate dataEmissione = LocalDate.now();
                                        PuntoDiEmissione puntoDiEmissione = puntoEmissioneDao.getPuntoEmissioneById(1);
                                        TitoloDiViaggio bigliettoCreato = new Biglietto(prezzo, dataEmissione, puntoDiEmissione);
                                        titolodiviaggioDao.saveTitoloDiViaggio(bigliettoCreato);
                                        System.out.println("Ecco a te il biglietto numero: " + bigliettoCreato.getId());
                                    } else {
                                        System.out.println("Operazione non valida! - devi selezionare una tra le opzioni disponibili");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Hai scelto di acquistare un abbonamento!");
                                    System.out.println("Per comprare un abbonamento inserire il numero tessera Utente: ");
                                    String tessera = scanner.nextLine();
                                    Tessera tesseraUtente = tesseraDao.getTesseraByNumeroTessera(tessera);

                                    System.out.println("Vuoi acquistare l'abbonamento in un distrubutore(1) o tramite un rivenditore(2)?");
                                    int sceltaLuogo2 = Integer.parseInt(scanner.nextLine());
                                    if (sceltaLuogo2 == 1) {
                                        System.out.println("Hai scelto di acquistare tramite un distrubutore Automatico!");
                                        double prezzo = 35.50;
                                        LocalDate dataEmissione = LocalDate.now();
                                        PuntoDiEmissione puntoDiEmissione = puntoEmissioneDao.getPuntoEmissioneById(2);
                                        System.out.println("Digita 1 per inserire un abbonamento MENSILE o 2 per inserire un abbonamento SETTIMANALE");
                                        int sceltaAbbonamento = Integer.parseInt(scanner.nextLine());
                                        if (sceltaAbbonamento == 1) {
                                            System.out.println("Hai scelto di acquistare un abbonamento MENSILE");
                                            TitoloDiViaggio abbonamentoCreato = new Abbonamento(prezzo, dataEmissione, puntoDiEmissione, tesseraUtente, TipoAbbonamento.MENSILE);
                                            titolodiviaggioDao.saveTitoloDiViaggio(abbonamentoCreato);
                                            System.out.println("Ecco a te l'abbonamento numero: " + abbonamentoCreato.getId());
                                        } else if (sceltaAbbonamento == 2) {
                                            System.out.println("Hai scelto di acquistare un abbonamento SETTIMANALE");
                                            TitoloDiViaggio abbonamentoCreato = new Abbonamento(prezzo, dataEmissione, puntoDiEmissione, tesseraUtente, TipoAbbonamento.SETTIMANALE);
                                            titolodiviaggioDao.saveTitoloDiViaggio(abbonamentoCreato);
                                            System.out.println("Ecco a te l'abbonamento numero: " + abbonamentoCreato.getId());
                                        }
                                    } else if (sceltaLuogo2 == 2) {
                                        System.out.println("Hai scelto di acquistare tramite un rivenditore!");
                                        double prezzo = 35.50;
                                        LocalDate dataEmissione = LocalDate.now();
                                        PuntoDiEmissione puntoDiEmissione = puntoEmissioneDao.getPuntoEmissioneById(2);
                                        System.out.println("Digita 1 per inserire un abbonamento MENSILE o 2 per inserire un abbonamento SETTIMANALE");
                                        int sceltaAbbonamento = Integer.parseInt(scanner.nextLine());
                                        if (sceltaAbbonamento == 1) {
                                            System.out.println("Hai scelto di acquistare un abbonamento MENSILE");
                                            TitoloDiViaggio abbonamentoCreato = new Abbonamento(prezzo, dataEmissione, puntoDiEmissione, tesseraUtente, TipoAbbonamento.MENSILE);
                                            titolodiviaggioDao.saveTitoloDiViaggio(abbonamentoCreato);
                                            System.out.println("Ecco a te l'abbonamento numero: " + abbonamentoCreato.getId());
                                        } else if (sceltaAbbonamento == 2) {
                                            System.out.println("Hai scelto di acquistare un abbonamento SETTIMANALE");
                                            TitoloDiViaggio abbonamentoCreato = new Abbonamento(prezzo, dataEmissione, puntoDiEmissione, tesseraUtente, TipoAbbonamento.SETTIMANALE);
                                            titolodiviaggioDao.saveTitoloDiViaggio(abbonamentoCreato);
                                            System.out.println("Ecco a te l'abbonamento numero: " + abbonamentoCreato.getId());
                                        }

                                    } else {
                                        System.out.println("Operazione non valida! - devi selezionare una tra le opzioni disponibili");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Hai scelto di vidimare il biglietto!");
                                    System.out.println("Inserisci il numero del biglietto");
                                    Long nBiglietto = scanner.nextLong();
                                    scanner.nextLine();
                                    Biglietto bigliettoUtente = titolodiviaggioDao.getBigliettoById(nBiglietto);
                                    System.out.println("Seleziona la linea desiderata(1 - 3)");
                                    System.out.println("1 -" + servizioDAO.getServizioById(1).getTratta().getZonaDiPartenza() + " " + servizioDAO.getServizioById(1).getTratta().getCapolinea());
                                    System.out.println("2 -" + servizioDAO.getServizioById(2).getTratta().getZonaDiPartenza() + " " + servizioDAO.getServizioById(2).getTratta().getCapolinea());
                                    System.out.println("3 -" + servizioDAO.getServizioById(3).getTratta().getZonaDiPartenza() + " " + servizioDAO.getServizioById(3).getTratta().getCapolinea());
                                    Long nLinea = scanner.nextLong();
                                    scanner.nextLine();
                                    if (nLinea == 1) {
                                        System.out.println("Hai scelto la prima linea come servizio");
                                        titolodiviaggioDao.vidimaBiglietto(bigliettoUtente, servizioDAO.getServizioById(nLinea));
                                    } else if (nLinea == 2) {
                                        System.out.println("Hai scelto la seconda linea come servizio");
                                        titolodiviaggioDao.vidimaBiglietto(bigliettoUtente, servizioDAO.getServizioById(nLinea));
                                    } else if (nLinea == 3) {
                                        System.out.println("Hai scelto la terza linea come servizio");
                                        titolodiviaggioDao.vidimaBiglietto(bigliettoUtente, servizioDAO.getServizioById(nLinea));
                                    } else {
                                        System.out.println("Operazione non valida! - devi selezionare una tra le opzioni disponibili");
                                    }
                                    break;
                            }

                            break;
                        case 2:
                            System.out.println("Inserisci il tuo id Utente per verificare privilegi: ");
                            Long codAmministratore = scanner.nextLong();
                            scanner.nextLine();
                            Utente utenteSuperiore = utenteDao.getUtenteById(codAmministratore);
                            if (utenteSuperiore.getAmministratore()) {
                                System.out.println("Benvenuto sul tuo profilo Amministratore");
                                System.out.println("⬇️MENU' GENERALE ADMIN⬇️");
                                System.out.println("1 ➡️ Eliminare Abbonamenti");
                                System.out.println("2 ➡️ Aggiungere/Eliminare Utenti");
                                System.out.println("3 ➡️ Aggiungere/Eliminare Tessere");
                                System.out.println("4 ➡️ Aggiungere/Eliminare Tratte");
                                System.out.println("5 ➡️ Aggiungere/Eliminare Mezzi");
                                System.out.println("6 ➡️ Visualizzare i titoli di viaggio emessi in un dato periodo");
                                System.out.println("7 ➡️ Ricerca il numero di tratte per mezzo ");
                                System.out.println("8 ➡️ Calcolo della media tempo di percorrenza di una tratta da un mezzo");
                                System.out.println("9 ➡️ Visualizza il tempo effettivo di percorrenza di una tratta/mezzo");
                                System.out.println("10 ➡️ Ricerca dei titoli di viaggio per punto di emissione");
                                System.out.println("11 ➡️ Verifica abbonamento dal numero di tessera");
                                System.out.println("12 ➡️ Ricerca dei biglietti vidimati in un periodo di tempo");

                                System.out.println("0 ➡️ Per uscire dal programma");

                                int sceltacase2 = Integer.parseInt(scanner.nextLine());
                                switch (sceltacase2) {
                                    case 0:
                                        System.out.println("Sei uscito correttamente dal programma! Torna presto!");
                                        exit = true;
                                        break;
                                    case 1:
                                        System.out.println("Hai scelto di eliminare l'abbonamento");
                                        System.out.println("Inserisci l'id dell'abbonamento: ");
                                        Long id = Long.parseLong(scanner.nextLine());
                                        titolodiviaggioDao.deleteTitoloDiViaggio(titolodiviaggioDao.getTitoloDiViaggioById(id));
                                        if (titolodiviaggioDao.getTitoloDiViaggioById(id) == null) {
                                            System.out.println("Abbonamento: " + id + " rimosso correttamente!");
                                        } else {
                                            System.out.println("Operazione fallita! ");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Per aggiungere un utente (1) o eliminare un utente (2): ");
                                        int scelta = Integer.parseInt(scanner.nextLine());
                                        if (scelta == 1) {
                                            System.out.println("Hai scelto di aggiungere un utente!");
                                            System.out.println("Inserisci nome utente: ");
                                            String nome = scanner.nextLine();
                                            System.out.println("Inserisci cognome utente: ");
                                            String cognome = scanner.nextLine();
                                            System.out.println("L'utente che stai creando sarà amministratore? Inserisci si o no");
                                            String amministratore = scanner.nextLine();
                                            if (amministratore.equals("si")) {
                                                utenteDao.saveUtente(new Utente(nome, cognome, null, true));
                                                System.out.println("Utente amministratore creato correttamente!");
                                            } else if (amministratore.equals("no")) {
                                                utenteDao.saveUtente(new Utente(nome, cognome, null, false));
                                                System.out.println("Utente semplice creato con successo!");
                                            } else {
                                                System.out.println("Valore inserito non valido!");
                                            }
                                        } else if (scelta == 2) {
                                            System.out.println("Hai scelto di eliminare un utente!");
                                            System.out.println("Inserisci l'id dell'utente da eliminare: ");
                                            Long id2 = Long.parseLong(scanner.nextLine());
                                            utenteDao.deleteUtente(utenteDao.getUtenteById(id2));
                                            if (utenteDao.getUtenteById(id2) == null) {
                                                System.out.println("Utente eliminato con successo! ");
                                            } else {
                                                System.out.println("L'utente non è stato eliminato! ");
                                            }
                                        } else {
                                            System.out.println("Operazione non valida! - devi selezionare una tra le opzioni disponibili");
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Per aggiungere una tessera (1) o per eliminare una tessera (2)");
                                        int scelta1 = Integer.parseInt(scanner.nextLine());
                                        if (scelta1 == 1) {
                                            System.out.println("Hai scelto di aggiungere una tessera!");
                                            String numeroTessera = faker.number().digits(8);
                                            tesseraDao.saveTessera(new Tessera(numeroTessera, LocalDate.now()));
                                            System.out.println("Nuova tessera creata con il numero: " + numeroTessera + "!");
                                        } else if (scelta1 == 2) {
                                            System.out.println("Hai scelto di eliminare una tessera!");
                                            System.out.println("Inserisci l'id della tessera da eliminare: ");
                                            Long idTessera = Long.parseLong(scanner.nextLine());
                                            tesseraDao.deleteTessera(tesseraDao.getTesseraById(idTessera));
                                            if (tesseraDao.getTesseraById(idTessera) == null) {
                                                System.out.println("Tessera eliminata con successo!");
                                            } else {
                                                System.out.println("Tessera non eliminata!");
                                            }
                                        } else {
                                            System.out.println("Operazione non valida! - devi selezionare una tra le opzioni disponibili");
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Per aggiungere una tratta (1) o per eliminare una tratta (2)");
                                        int scelta2 = Integer.parseInt(scanner.nextLine());
                                        if (scelta2 == 1) {
                                            System.out.println("Inserisci una zona di partenza: ");
                                            String zonaDiPartenza = scanner.nextLine();
                                            System.out.println("Inserisci il capolinea: ");
                                            String capolinea = scanner.nextLine();
                                            System.out.println("Inserisci il tempo previsto di percorrenza: ");
                                            int tempoPrevistoPercorrenza = Integer.parseInt(scanner.nextLine());
                                            trattaDAO.saveTratta(new Tratta(zonaDiPartenza, capolinea, tempoPrevistoPercorrenza));
                                            System.out.println("Tratta aggiunta con successo! ");
                                        } else if (scelta2 == 2) {
                                            System.out.println("Hai scelto di eliminare una tratta!");
                                            System.out.println("Inserisci l'id della tratta da eliminare:");
                                            Long idTratta = Long.parseLong(scanner.nextLine());
                                            trattaDAO.deleteTratta(trattaDAO.getTrattaById(idTratta));
                                            if (trattaDAO.getTrattaById(idTratta) == null) {
                                                System.out.println("Tratta eliminata con successo!");
                                            } else {
                                                System.out.println("Tratta non eliminata!");
                                            }
                                        } else {
                                            System.out.println("Operazione non valida! - devi selezionare una tra le opzioni disponibili");
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Per aggiungere un mezzo (1) o per eliminare un mezzo (2)");
                                        int scelta3 = Integer.parseInt(scanner.nextLine());
                                        if (scelta3 == 1) {
                                            System.out.println("Hai scelto di aggiungere un mezzo!");
                                            System.out.println("Che mezzo vuoi aggiungere? (1) tram o (2) autobus");
                                            int mezzo = Integer.parseInt(scanner.nextLine());
                                            if (mezzo == 1) {
                                                System.out.println("Inserisci la capienza del mezzo: ");
                                                int capienza = Integer.parseInt(scanner.nextLine());
                                                mezzoDao.saveMezzo(new Tram(capienza, StatoMezzo.IN_SERVIZIO));
                                                System.out.println("Tram creato con successo");
                                            } else if (mezzo == 2) {
                                                System.out.println("Inserisci la capienza del mezzo: ");
                                                int capienza = Integer.parseInt(scanner.nextLine());
                                                mezzoDao.saveMezzo(new Autobus(capienza, StatoMezzo.IN_SERVIZIO));
                                                System.out.println("Autobus creato con successo");
                                            } else {
                                                System.out.println("Operazione non valida! - devi selezionare una tra le opzioni disponibili");
                                            }
                                        } else if (scelta3 == 2) {
                                            System.out.println("Hai scelto di eliminare un mezzo!");
                                            System.out.println("Inserisci l'id del mezzo da eliminare: ");
                                            Long idMezzo = Long.parseLong(scanner.nextLine());
                                            mezzoDao.deleteMezzo(mezzoDao.getMezzoById(idMezzo));
                                            if (mezzoDao.getMezzoById(idMezzo) == null) {
                                                System.out.println("Mezzo eliminato con successo!");
                                            } else {
                                                System.out.println("Mezzo non eliminato!");
                                            }
                                        } else {
                                            System.out.println("Operazione non valida! - devi selezionare una tra le opzioni disponibili");
                                        }
                                        break;
                                    case 6:
                                        System.out.println("Hai scelto di visualizzare i titoli di viaggio emessi in un periodo!");
                                        System.out.println("Inserisci la data di inizio del periodo: ");
                                        LocalDate dataInizio = LocalDate.parse(scanner.nextLine());
                                        System.out.println("Inserisci la data di fine del periodo: ");
                                        LocalDate dataFine = LocalDate.parse(scanner.nextLine());
                                        List<TitoloDiViaggio> lista = titolodiviaggioDao.ricercaListaTitoliViaggioEmessiPerPeriodo(dataInizio, dataFine);
                                        lista.forEach(System.out::println);
                                        break;
                                    case 7:
                                        System.out.println("Hai scelto di ricercare il numero di tratte per mezzo!");
                                        System.out.println("Inserisci l'id del mezzo per il quale vuoi effettuare la ricerca: ");
                                        Long idMezzo = Long.parseLong(scanner.nextLine());
                                        System.out.println("Inserisci l'id della tratta per il quale vuoi effettuare la ricerca: ");
                                        Long idTratta = Long.parseLong(scanner.nextLine());
                                        Long numeroTratte = servizioDAO.getNumeroTrattePerMezzo(mezzoDao.getMezzoById(idMezzo), trattaDAO.getTrattaById(idTratta));
                                        System.out.println("Numero di tratte percorse: " + numeroTratte);
                                        break;
                                    case 8:
                                        System.out.println("Hai scelto di calcolare la media di tempo di percorrenza di una tratta da un mezzo");
                                        System.out.println("Inserisci l'id del mezzo per effetturare la media: ");
                                        Long idMezzo1 = Long.parseLong(scanner.nextLine());
                                        System.out.println("Inserisci l'id della tratta per effettuare la media: ");
                                        Long idTratta1 = Long.parseLong(scanner.nextLine());
                                        Double mediaTratte = servizioDAO.getTempoMedioEffettivoPerTratta(mezzoDao.getMezzoById(idMezzo1), trattaDAO.getTrattaById(idTratta1));
                                        System.out.println("Il tempo medio di percorrenza delle tratte richieste è: " + mediaTratte + " minuti.");
                                        break;
                                    case 9:
                                        System.out.println("Hai scelto di visualizzare il tempo effettivo di percorrenza di una tratta/mezzo");
                                        System.out.println("Inserisci l'id del mezzo per visualizzare il tempo: ");
                                        Long idMezzo2 = Long.parseLong(scanner.nextLine());
                                        System.out.println("Inserisci l'id della tratta per visualizzare il tempo: ");
                                        Long idTratta2 = Long.parseLong(scanner.nextLine());
                                        List<Servizio> listaServiziTempiEffettivi = servizioDAO.getListaTratteETempiEffettiviPerMezzo(mezzoDao.getMezzoById(idMezzo2), trattaDAO.getTrattaById(idTratta2));
                                        for (Servizio servizio : listaServiziTempiEffettivi) {
                                            System.out.println("Tempo Effettivo della " + servizio.getTratta() + " è di " + servizio.getTempoEffettivo() + " minuti");
                                        }
                                        break;
                                    case 10:
                                        System.out.println("Hai scelto la ricerca di titoli di viaggio per punto di emissione");
                                        System.out.println("Inserisci l'id del punto di emissione: ");
                                        Long idEmissione = Long.parseLong(scanner.nextLine());
                                        System.out.println("Inserisci la data di inizio della ricerca: ");
                                        LocalDate dataInizio1 = LocalDate.parse(scanner.nextLine());
                                        System.out.println("Inserisci la data di fine della ricerca: ");
                                        LocalDate dataFine1 = LocalDate.parse(scanner.nextLine());

                                        List<TitoloDiViaggio> lista1 = titolodiviaggioDao.ricercaTitoliViaggioPerPuntoEmissione(puntoEmissioneDao
                                                .getPuntoEmissioneById(idEmissione), dataInizio1, dataFine1);
                                        lista1.forEach(System.out::println);

                                        break;
                                    case 11:
                                        System.out.println("Hai selezionato la verifica dell'abbonamento valido in base al numero di tessera!");
                                        System.out.println("Inserisci il numero di tessera: ");
                                        String idTessera = scanner.nextLine();
                                        System.out.println(titolodiviaggioDao.verificaValiditàAbbonamentoTramiteNumeroTessera(tesseraDao.getTesseraByNumeroTessera(idTessera)));

                                        break;
                                    case 12:
                                        System.out.println("Hai selezionato la ricerca di biglietti vidimati in un periodo di tempo");
                                        System.out.println("Inserisci la data dalla quale vuoi iniziare la ricerca: ");
                                        LocalDate inizioPeriodo = LocalDate.parse(scanner.nextLine());
                                        System.out.println("Inserisci la data di fine ricerca: ");
                                        LocalDate finePeriodo = LocalDate.parse(scanner.nextLine());
                                        System.out.println("I biglietti sono: ");
                                        List<TitoloDiViaggio> lista2 = titolodiviaggioDao.getBigliettiVidimatiPerDate(inizioPeriodo, finePeriodo);
                                        lista2.forEach(System.out::println);
                                        break;

                                    default:
                                        System.out.println("Operazione non valida! - devi selezionare una tra le opzioni disponibili");
                                        break;

                                }
                            } else {
                                System.out.println("Non hai i permessi per accedere a questa sezione!");
                            }


                            break;
                        default:
                            System.out.println("Operazione non valida");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Il valore da inserire deve essere numerico .errore: " + e.getMessage());
                }catch(InputMismatchException e){
                    System.out.println("L'input inserito non valido!" + e.getMessage());
                }catch(RuntimeException e){
                    System.out.println(e.getMessage());
                }catch (StackOverflowError e){
                    System.out.println(e.getMessage());
                }catch(Exception e){
                    System.out.println("Errore: " + e.getMessage());
                }
            }







        em.close();
        emf.close();
    }
}
