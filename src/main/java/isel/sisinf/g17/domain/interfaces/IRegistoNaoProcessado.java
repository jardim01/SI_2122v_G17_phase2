package isel.sisinf.g17.domain.interfaces;

import isel.sisinf.g17.domain.Equipamento;

import java.sql.Timestamp;

public interface IRegistoNaoProcessado {
    long getId();

    Timestamp getMarcaTemporal();

    Equipamento getEquipamento();

    Double getLatitude();

    Double getLongitude();

    void setMarcaTemporal(Timestamp marcaTemporal);

    void setEquipamento(Equipamento equipamento);

    void setLatitude(Double latitude);

    void setLongitude(Double longitude);
}
