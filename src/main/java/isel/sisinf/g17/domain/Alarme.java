package isel.sisinf.g17.domain;

import isel.sisinf.g17.domain.interfaces.IAlarme;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "alarmes")
@NamedQuery(name = "Alarme.findByKey", query = "SELECT a FROM Alarme a WHERE a.registo.id = :key")
public class Alarme implements IAlarme {
    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_reg")
    private RegistoProcessado registo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "matricula")
    private Veiculo veiculo;
    @Column(name = "marca_temporal")
    private Timestamp marcaTemporal;

    @Override
    public RegistoProcessado getRegisto() {
        return registo;
    }

    @Override
    public Veiculo getVeiculo() {
        return veiculo;
    }

    @Override
    public Timestamp getMarcaTemporal() {
        return marcaTemporal;
    }

    @Override
    public void setRegisto(RegistoProcessado registo) {
        this.registo = registo;
    }

    @Override
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public void setMarcaTemporal(Timestamp marcaTemporal) {
        this.marcaTemporal = marcaTemporal;
    }

    @Override
    public String toString() {
        return String.format(
                "Alarme(reg=%d, matricula=%s, marcaTemporal=%s)",
                registo.getId(), veiculo.getMatricula(), marcaTemporal
        );
    }
}
