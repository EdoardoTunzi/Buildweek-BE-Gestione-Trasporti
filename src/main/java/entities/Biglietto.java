package entities;

import enumerated.TipoBiglietto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto extends TitoloDiViaggio {
    @Column(nullable = false)
    private Boolean isVidimato;
    @Column(nullable = false)
    private Boolean isValid;

    private LocalDate dataVidimazione;

    public Biglietto() {
    }

    public Biglietto(Double prezzo, LocalDate dataEmissione, PuntoDiEmissione puntoDiEmissione) {
        super(prezzo, dataEmissione, puntoDiEmissione);
        this.isVidimato = false;
        this.isValid = true;
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

    public LocalDate getDataVidimazione() {
        return dataVidimazione;
    }

    public void setDataVidimazione(LocalDate dataVidimazione) {
        this.dataVidimazione = dataVidimazione;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "isVidimato=" + isVidimato +
                ", isValid=" + isValid +
                "} " + super.toString();
    }
}
