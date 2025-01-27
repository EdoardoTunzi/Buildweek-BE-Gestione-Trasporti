package entities;

import javax.persistence.Entity;

@Entity
public class DistributoreAutomatico extends PuntoDiEmissione {

    private Boolean isAttivo;

    public DistributoreAutomatico() {
    }

    public DistributoreAutomatico(String nome, Boolean isAttivo) {
        super(nome);
        this.isAttivo = isAttivo;
    }

    public Boolean getAttivo() {
        return isAttivo;
    }

    public void setAttivo(Boolean attivo) {
        isAttivo = attivo;
    }

    @Override
    public String toString() {
        return "DistributoreAutomatico{" +
                "isAttivo=" + isAttivo +
                '}';
    }
}
