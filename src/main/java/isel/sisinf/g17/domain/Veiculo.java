package isel.sisinf.g17.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculos")
public class Veiculo implements IVeiculo {
    @Id
    private String matricula;
    @Column(name = "nome_cond_atual")
    private String nomeCondutorAtual;
    @Column(name = "telef_cond_actual")
    private int telefoneCondutorAtual;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_equip")
    private Equipamento equipamento;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_frota")
    private Frota frota;
    @Column(name = "n_alarmes")
    private int numeroAlarmes;

    @Override
    public String getMatricula() {
        return matricula;
    }

    @Override
    public String getNomeCondutorAtual() {
        return nomeCondutorAtual;
    }

    @Override
    public int getTelefoneCondutorAtual() {
        return telefoneCondutorAtual;
    }

    @Override
    public Equipamento getEquipamento() {
        return equipamento;
    }

    @Override
    public Frota getFrota() {
        return frota;
    }

    @Override
    public int getNumeroAlarmes() {
        return numeroAlarmes;
    }

    @Override
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public void setNomeCondutorAtual(String nomeCondutorAtual) {
        this.nomeCondutorAtual = nomeCondutorAtual;
    }

    @Override
    public void setTelefoneCondutorAtual(int telefoneCondutorAtual) {
        this.telefoneCondutorAtual = telefoneCondutorAtual;
    }

    @Override
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public void setFrota(Frota frota) {
        this.frota = frota;
    }
}
