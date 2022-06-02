package isel.sisinf.g17.domain;

public interface IVeiculo {
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

