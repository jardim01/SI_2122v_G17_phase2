package isel.sisinf.g17.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "estados_equipamentos")
@NamedQuery(name = "Estado.findByKey", query = "SELECT e FROM Estado e WHERE e.estado = :key")
public class Estado implements IEstado {
    @Id
    private String estado;

    @Override
    public String getEstado() {
        return estado;
    }

    @Override
    public void setEstado(String estado) {
        if (estado.length() > MAX_ESTADO_LENGTH)
            throw new IllegalArgumentException("The given estado is too long");
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("Estado(estado=%s)", estado);
    }
}
