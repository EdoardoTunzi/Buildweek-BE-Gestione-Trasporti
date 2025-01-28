package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servizi")
public class Servizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mezzo mezzo;

    @ManyToOne
    private Tratta tratta;

    private int tempoEffettivo;

    @OneToMany
    private List<Biglietto> bigliettiVidimati;

    public Servizio() {
    }

    public Servizio(Mezzo mezzo, Tratta tratta, int tempoEffettivo) {
        this.mezzo = mezzo;
        this.tratta = tratta;
        this.tempoEffettivo = tempoEffettivo;
        this.bigliettiVidimati = new ArrayList<>();
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

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public int getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(int tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    public List<Biglietto> getBigliettiVidimati() {
        return bigliettiVidimati;
    }

    public void setBigliettiVidimati(List<Biglietto> bigliettiVidimati) {
        this.bigliettiVidimati = bigliettiVidimati;
    }

    @Override
    public String toString() {
        return "Servizio{" +
                "id=" + id +
                ", mezzo=" + mezzo +
                ", tratta=" + tratta +
                ", tempoEffettivo=" + tempoEffettivo +
                ", bigliettiVidimati=" + bigliettiVidimati +
                '}';
    }
}