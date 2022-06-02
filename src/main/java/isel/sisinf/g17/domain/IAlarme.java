package isel.sisinf.g17.domain;

import java.sql.Timestamp;

public interface IAlarme {
    RegistoProcessado getRegisto();

    Veiculo getVeiculo();

    Timestamp getMarcaTemporal();

    void setRegisto(RegistoProcessado registo);

    void setVeiculo(Veiculo veiculo);

    void setMarcaTemporal(Timestamp marcaTemporal);
}
