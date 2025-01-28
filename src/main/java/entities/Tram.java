package entities;

import enumerated.StatoMezzo;

import javax.persistence.Entity;

@Entity
public class Tram extends Mezzo{

    public Tram() {
    }

    public Tram(int capienza, StatoMezzo statoMezzo) {
        super(capienza, statoMezzo);
    }

    @Override
    public String toString() {
        return "Tram: " + super.toString();
    }
}
