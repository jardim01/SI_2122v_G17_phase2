package isel.sisinf.g17.domain;

import java.sql.Timestamp;

public interface IRegistoProcessado {
    long getId();
    Timestamp getMarcaTemporal();
    Equipamento getEquipamento();
    // TODO: probably not double
    double getLatitude();
    double getLongitude();
    void setId(long id);
    void setMarcaTemporal(Timestamp marcaTemporal);
    void setEquipamento(Equipamento equipamento);
    // TODO: probably not double
    void setLatitude(double latitude);
    void setLongitude(double longitude);
}
