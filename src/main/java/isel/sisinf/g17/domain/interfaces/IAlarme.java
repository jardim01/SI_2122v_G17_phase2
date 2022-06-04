package isel.sisinf.g17.domain.interfaces;

import isel.sisinf.g17.domain.RegistoProcessado;
import isel.sisinf.g17.domain.Veiculo;

import java.sql.Timestamp;

public interface IAlarme {
    RegistoProcessado getRegisto();

    Veiculo getVeiculo();

    Timestamp getMarcaTemporal();

    void setRegisto(RegistoProcessado registo);

    void setVeiculo(Veiculo veiculo);

    void setMarcaTemporal(Timestamp marcaTemporal);
}
