package entities;

import enumerated.TipoBiglietto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto extends TitoloDiViaggio {

    private Boolean isTimbrato;

    @Enumerated(EnumType.STRING)
    private TipoBiglietto tratta;

    public Biglietto() {
    }

    public Biglietto(Double prezzo, LocalDate dataEmissione, PuntoDiEmissione puntoDiEmissione, Boolean isTimbrato, TipoBiglietto tratta) {
        super(prezzo, dataEmissione, puntoDiEmissione);
        this.isTimbrato = isTimbrato;
        this.tratta = tratta;
    }

    public Boolean getTimbrato() {
        return isTimbrato;
    }

    public void setTimbrato(Boolean timbrato) {
        isTimbrato = timbrato;
    }

    public TipoBiglietto getTratta() {
        return tratta;
    }

    public void setTratta(TipoBiglietto tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "isTimbrato=" + isTimbrato +
                ", tratta=" + tratta +
                '}';
    }
}
