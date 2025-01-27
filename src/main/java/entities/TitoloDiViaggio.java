package entities;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class TitoloDiViaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double prezzo;

    @Column(nullable = false)
    private LocalDate dataEmissione;

    @ManyToOne
    @JoinColumn(name = "punto_emissione_id", nullable = false)
    private PuntoDiEmissione puntoDiEmissione;



    public TitoloDiViaggio(Double prezzo, LocalDate dataEmissione, PuntoDiEmissione puntoDiEmissione) {
        this.prezzo = prezzo;
        this.dataEmissione = dataEmissione;
        this.puntoDiEmissione = puntoDiEmissione;
    }

    public TitoloDiViaggio() {
    }


    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }


    public PuntoDiEmissione getPuntoDiEmissione() {
        return puntoDiEmissione;
    }

    public void setPuntoDiEmissione(PuntoDiEmissione puntoDiEmissione) {
        this.puntoDiEmissione = puntoDiEmissione;
    }


    @Override
    public String toString() {
        return "TitoloDiViaggio{" +
                "id=" + id +
                ", prezzo=" + prezzo +
                ", dataEmissione=" + dataEmissione +
                ", puntoDiEmissione=" + puntoDiEmissione +
                '}';
    }
}
