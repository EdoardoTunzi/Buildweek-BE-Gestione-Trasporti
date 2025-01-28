package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "manutenzioni")
public class Manutenzione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mezzo mezzo;

    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Manutenzione() {
    }

    public Manutenzione(Mezzo mezzo, LocalDate dataInizio, LocalDate dataFine) {
        this.mezzo = mezzo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "id=" + id +
                ", mezzo=" + mezzo +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}