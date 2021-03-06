package isel.sisinf.g17.domain;

import isel.sisinf.g17.domain.interfaces.IEquipamento;
import jakarta.persistence.*;

@Entity
@Table(name = "equipamentos")
@NamedQuery(name = "Equipamento.findByKey", query = "SELECT e FROM Equipamento e WHERE e.id = :key")
public class Equipamento implements IEquipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "estado")
    private Estado estado;

    public Equipamento() {

    }

    public Equipamento(Estado estado) {
        super();

        setEstado(estado);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Estado getEstado() {
        return estado;
    }


    @Override
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format(
                "Equipamento(id=%d, estado=%s)",
                id, estado.getEstado()
        );
    }
}

