package entities;

import enumerated.StatoMezzo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Autobus extends Mezzo{

    public Autobus() {
    }

    public Autobus(int capienza, StatoMezzo statoMezzo) {
        super(capienza, statoMezzo);
    }

    @Override
    public String toString() {
        return "Autobus: " + super.toString();
    }
}
