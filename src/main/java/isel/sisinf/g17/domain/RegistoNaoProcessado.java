package isel.sisinf.g17.domain;

import isel.sisinf.g17.domain.interfaces.IRegistoNaoProcessado;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "registos_nao_processados")
@NamedQuery(name = "RegistoNaoProcessado.findByKey", query = "SELECT r FROM RegistoNaoProcessado r WHERE r.id = :key")
public class RegistoNaoProcessado implements IRegistoNaoProcessado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reg")
    private long id;
    @Column(name = "marca_temporal")
    private Timestamp marcaTemporal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_equip")
    private Equipamento equipamento;
    private Double latitude;
    private Double longitude;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Timestamp getMarcaTemporal() {
        return marcaTemporal;
    }

    @Override
    public Equipamento getEquipamento() {
        return equipamento;
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setMarcaTemporal(Timestamp marcaTemporal) {
        this.marcaTemporal = marcaTemporal;
    }

    @Override
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public void setLatitude(Double latitude) {
        if (!Validation.latitudeValida(latitude))
            throw new IllegalArgumentException("Invalid value");
        this.latitude = latitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        if (!Validation.longitudeValida(longitude))
            throw new IllegalArgumentException("Invalid value");
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        Long equipId = null;
        if (equipamento != null) equipId = equipamento.getId();
        return String.format(
                "RegistoNaoProcessado(id=%d, idEquip=%d, marcaTemporal=%s, latitude=%f, longitude=%f)",
                id, equipId, marcaTemporal, latitude, longitude
        );
    }
}
