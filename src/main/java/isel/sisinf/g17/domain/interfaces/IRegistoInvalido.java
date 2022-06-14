package isel.sisinf.g17.domain.interfaces;

import java.sql.Timestamp;

public interface IRegistoInvalido {
    Long getIdEquip();

    Timestamp getMarcaTemporal();

    Double getLatitude();

    Double getLongitude();

    void setMarcaTemporal(Timestamp marcaTemporal);

    void setIdEquip(Long idEquip);

    void setLatitude(Double latitude);

    void setLongitude(Double longitude);
}
