package org.example;


import UI.InterfacciaUser;
import com.github.javafaker.Faker;
import Dao.*;
import entities.*;
import enumerated.StatoMezzo;
import enumerated.TipoAbbonamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Locale;


public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aziendaTrasporti");
    private static EntityManager em = emf.createEntityManager();
    private static Faker faker = new Faker(new Locale("it"));
    private static InterfacciaUser intUser = new InterfacciaUser();

    public static void main(String[] args) {
        //Metodo per creazione DB
        intUser.inizializzazioneDB();
        //Interfaccia Utente
        intUser.gestioneUI();

    }
}
