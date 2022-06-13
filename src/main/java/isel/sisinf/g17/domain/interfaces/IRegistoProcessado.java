package isel.sisinf.g17.domain.interfaces;

import isel.sisinf.g17.domain.Equipamento;

import java.sql.Timestamp;

public interface IRegistoProcessado {
    long getId();

    Timestamp getMarcaTemporal();

    Equipamento getEquipamento();

    double getLatitude();

    double getLongitude();

    void setMarcaTemporal(Timestamp marcaTemporal);

    void setEquipamento(Equipamento equipamento);

    void setLatitude(double latitude);

    void setLongitude(double longitude);
}
