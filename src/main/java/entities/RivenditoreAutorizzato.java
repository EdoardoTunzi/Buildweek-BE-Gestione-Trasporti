package entities;

import javax.persistence.Entity;

@Entity
public class RivenditoreAutorizzato extends PuntoDiEmissione {

    private String indirizzo;

    public RivenditoreAutorizzato() {
    }

    public RivenditoreAutorizzato(String nome, String indirizzo) {
        super(nome);
        this.indirizzo = indirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString() {
        return "RivenditoreAutorizzato{" +
                "indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
