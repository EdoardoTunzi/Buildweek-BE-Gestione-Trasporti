package entities;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @OneToOne(cascade = CascadeType.ALL)
    private Tessera tesseraUtente;

    @Column(nullable = false)
    private Boolean isAmministratore;


    public Utente() {
    }

    public Utente(String nome, String cognome, Tessera tesseraUtente, Boolean isAmministratore) {
        this.nome = nome;
        this.cognome = cognome;
        this.tesseraUtente = tesseraUtente;
        this.isAmministratore = isAmministratore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Tessera getTesseraUtente() {
        return tesseraUtente;
    }

    public void setTesseraUtente(Tessera tesseraUtente) {
        this.tesseraUtente = tesseraUtente;
    }

    public Boolean getAmministratore() {
        return isAmministratore;
    }

    public void setAmministratore(Boolean amministratore) {
        isAmministratore = amministratore;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", tesseraUtente=" + tesseraUtente +
                ", isAmministratore=" + isAmministratore +
                '}';
    }
}
