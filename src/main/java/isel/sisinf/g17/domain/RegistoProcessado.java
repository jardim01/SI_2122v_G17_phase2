package isel.sisinf.g17.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "registos_processados")
public class RegistoProcessado implements IRegistoProcessado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reg")
    private long id;
    @Column(name = "marca_temporal_proc")
    private Timestamp marcaTemporal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_equip")
    private Equipamento equipamento;
    private double latitude;
    private double longitude;

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
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public void setId(long id) {
        this.id = id;
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
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}