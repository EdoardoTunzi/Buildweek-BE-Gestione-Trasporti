package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tratte")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String zonaDiPartenza;
    private String capolinea;
    private int TempoPrevistoDiPercorrenza;

    @OneToMany(mappedBy = "tratta")
    private List<Servizio> listaServiziPerTratta;

    public Tratta() {
    }

    public Tratta(String zonaDiPartenza, String capolinea, int tempoPrevistoDiPercorrenza) {
        this.zonaDiPartenza = zonaDiPartenza;
        this.capolinea = capolinea;
        TempoPrevistoDiPercorrenza = tempoPrevistoDiPercorrenza;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZonaDiPartenza() {
        return zonaDiPartenza;
    }

    public void setZonaDiPartenza(String zonaDiPartenza) {
        this.zonaDiPartenza = zonaDiPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public int getTempoPrevistoDiPercorrenza() {
        return TempoPrevistoDiPercorrenza;
    }

    public void setTempoPrevistoDiPercorrenza(int tempoPrevistoDiPercorrenza) {
        TempoPrevistoDiPercorrenza = tempoPrevistoDiPercorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zonaDiPartenza='" + zonaDiPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", TempoPrevistoDiPercorrenza=" + TempoPrevistoDiPercorrenza +
                '}';
    }
}
