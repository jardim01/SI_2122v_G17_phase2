package isel.sisinf.g17.domain;

import isel.sisinf.g17.domain.interfaces.IFrota;
import jakarta.persistence.*;

@Entity
@Table(name = "frotas_veiculos")
@NamedQueries(
        value = {
                @NamedQuery(name = "Frota.findByKey", query = "SELECT f FROM Frota f WHERE f.id = :key"),
                @NamedQuery(name = "Frota.findByNif", query = "SELECT f FROM Frota f WHERE f.nif = :nif")
        }
)

public class Frota implements IFrota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nif_cliente")
    private int nif;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public int getNif() {
        return nif;
    }

    @Override
    public void setNif(int nif) {
        this.nif = nif;
    }

    @Override
    public String toString() {
        return String.format("Frota(id=%d)", id);
    }
}
