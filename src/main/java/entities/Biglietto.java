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

    private Boolean isVidimato;

    private Boolean isValid;

    @Enumerated(EnumType.STRING)
    private TipoBiglietto trattaBiglietto;


    public Biglietto() {
    }

    public Biglietto(Double prezzo, LocalDate dataEmissione, PuntoDiEmissione puntoDiEmissione, TipoBiglietto tratta) {
        super(prezzo, dataEmissione, puntoDiEmissione);
        this.isVidimato = false;
        this.isValid = true;
        this.trattaBiglietto = tratta;
    }

    public Boolean getVidimato() {
        return isVidimato;
    }

    public void setVidimato(Boolean vidimato) {
        isVidimato = vidimato;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public TipoBiglietto getTrattaBiglietto() {
        return trattaBiglietto;
    }

    public void setTrattaBiglietto(TipoBiglietto trattaBiglietto) {
        this.trattaBiglietto = trattaBiglietto;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "isVidimato=" + isVidimato +
                ", isValid=" + isValid +
                ", trattaBiglietto=" + trattaBiglietto +
                "} " + super.toString();
    }
}
