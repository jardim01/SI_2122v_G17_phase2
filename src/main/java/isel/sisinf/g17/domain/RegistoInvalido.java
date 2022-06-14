package isel.sisinf.g17.domain;

import isel.sisinf.g17.domain.interfaces.IRegistoInvalido;
import isel.sisinf.g17.domain.interfaces.IRegistoProcessado;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "registos_invalidos")
public class RegistoInvalido implements IRegistoInvalido {
    @Column(name = "equip_id")
    private Long idEquip;
    @Id
    @Column(name = "marca_temporal_inval")
    private Timestamp marcaTemporal;
    private Double latitude;
    private Double longitude;

    @Override
    public Long getIdEquip() {
        return idEquip;
    }

    @Override
    public Timestamp getMarcaTemporal() {
        return marcaTemporal;
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
    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
