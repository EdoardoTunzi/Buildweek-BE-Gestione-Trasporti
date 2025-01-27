package entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tessere")

public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroTessera;

    @Column(nullable = false)
    private LocalDate dataAttivazione;

    @Column
    private LocalDate dataScadenza;


    public Tessera() {
    }

    public Tessera(String numeroTessera, LocalDate dataAttivazione) {
        this.numeroTessera = numeroTessera;
        this.dataAttivazione = dataAttivazione;
        this.dataScadenza = dataAttivazione.plusDays(365);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(String numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public LocalDate getDataAttivazione() {
        return dataAttivazione;
    }

    public void setDataAttivazione(LocalDate dataAttivazione) {
        this.dataAttivazione = dataAttivazione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }



    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", numeroTessera='" + numeroTessera + '\'' +
                ", dataAttivazione=" + dataAttivazione +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
