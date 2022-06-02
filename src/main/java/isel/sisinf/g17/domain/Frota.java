package isel.sisinf.g17.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "frotas_veiculos")
@NamedQuery(name = "Frota.findByKey", query = "SELECT f FROM Frota f WHERE f.id = :key")
public class Frota implements IFrota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Frota(id=%d)", id);
    }
}
