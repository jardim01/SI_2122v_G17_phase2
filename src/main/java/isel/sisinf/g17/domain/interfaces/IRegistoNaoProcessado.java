package isel.sisinf.g17.domain.interfaces;

import isel.sisinf.g17.domain.Equipamento;

import java.sql.Timestamp;

public interface IRegistoNaoProcessado {
    long getId();

    Timestamp getMarcaTemporal();

    Equipamento getEquipamento();

    // TODO: probably not double
    double getLatitude();

    double getLongitude();

    void setMarcaTemporal(Timestamp marcaTemporal);

    void setEquipamento(Equipamento equipamento);

    // TODO: probably not double
    void setLatitude(double latitude);

    void setLongitude(double longitude);
}
