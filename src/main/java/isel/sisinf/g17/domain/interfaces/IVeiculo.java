package isel.sisinf.g17.domain.interfaces;

import isel.sisinf.g17.domain.Equipamento;
import isel.sisinf.g17.domain.Frota;

public interface IVeiculo {
    int MAX_NAME_LENGTH = 60;

    String getMatricula();

    String getNomeCondutorAtual();

    int getTelefoneCondutorAtual();

    Equipamento getEquipamento();

    Frota getFrota();

    int getNumeroAlarmes();

    void setMatricula(String matricula);

    void setNomeCondutorAtual(String nomeCondutorAtual);

    void setTelefoneCondutorAtual(int telefoneCondutorAtual);

    void setEquipamento(Equipamento equipamento);

    void setFrota(Frota frota);
}

