package entities;

import enumerated.StatoMezzo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int capienza;

    @Enumerated(EnumType.STRING)
    private StatoMezzo statoMezzo;
    @OneToMany(mappedBy = "mezzo")
    private List<Manutenzione> listaManutenzioni;
    @OneToMany(mappedBy = "mezzo")
    private List<Servizio> listaServizi;


    public Mezzo() {
    }

    public Mezzo(int capienza, StatoMezzo statoMezzo) {
        this.capienza = capienza;
        this.statoMezzo = statoMezzo;
        this.listaManutenzioni = new ArrayList<>();
        this.listaServizi = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public StatoMezzo getStatoMezzo() {
        return statoMezzo;
    }

    public void setStatoMezzo(StatoMezzo statoMezzo) {
        this.statoMezzo = statoMezzo;
    }

    public List<Manutenzione> getListaManutenzioni() {
        return listaManutenzioni;
    }

    public void setListaManutenzioni(List<Manutenzione> listaManutenzioni) {
        this.listaManutenzioni = listaManutenzioni;
    }

    public List<Servizio> getListaServizi() {
        return listaServizi;
    }

    public void setListaServizi(List<Servizio> listaServizi) {
        this.listaServizi = listaServizi;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", statoMezzo=" + statoMezzo +
                ", listaManutenzioni=" + listaManutenzioni +
                ", listaServizi=" + listaServizi +
                '}';
    }
}
