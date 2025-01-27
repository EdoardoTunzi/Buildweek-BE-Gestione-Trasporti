package entities;

import enumerated.TipoAbbonamento;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento extends TitoloDiViaggio {

    @OneToOne
    @JoinColumn(name = "tessera_id")
    private Tessera tesseraUtente;

    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipoAbbonamento;

    private LocalDate dataFineValidità;

    public Abbonamento() {
    }

    public Abbonamento(Double prezzo, LocalDate dataEmissione, PuntoDiEmissione puntoDiEmissione, Tessera tesseraUtente, TipoAbbonamento tipoAbbonamento) {
        super(prezzo, dataEmissione, puntoDiEmissione);
        this.tesseraUtente = tesseraUtente;
        this.tipoAbbonamento = tipoAbbonamento;
        if (this.tipoAbbonamento == TipoAbbonamento.SETTIMANALE) {
            this.dataFineValidità = dataEmissione.plusDays(7);
        } else if (this.tipoAbbonamento == TipoAbbonamento.MENSILE) {
            this.dataFineValidità = dataEmissione.plusDays(30);
        }
    }

    public LocalDate getDataFineValidità() {
        return dataFineValidità;
    }

    public void setDataFineValidità(LocalDate dataFineValidità) {
        this.dataFineValidità = dataFineValidità;
    }

    public Tessera getTesseraUtente() {
        return tesseraUtente;
    }

    public void setTesseraUtente(Tessera tesseraUtente) {
        this.tesseraUtente = tesseraUtente;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "tesseraUtente=" + tesseraUtente +
                ", tipoAbbonamento=" + tipoAbbonamento +
                '}';
    }
}
