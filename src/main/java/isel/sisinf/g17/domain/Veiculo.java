package isel.sisinf.g17.domain;

import isel.sisinf.g17.domain.interfaces.IVeiculo;
import jakarta.persistence.*;

@Entity
@Table(name = "veiculos")
@NamedQuery(name = "Veiculo.findByKey", query = "SELECT v FROM Veiculo v WHERE v.matricula = :key")
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
    public void setMatricula(String matricula) {
        if (!Validation.matriculaValida(matricula))
            throw new IllegalArgumentException("Invalid license plate format");
        this.matricula = matricula;
    }

    @Override
    public void setNomeCondutorAtual(String nomeCondutorAtual) {
        if (!Validation.nomeValido(nomeCondutorAtual))
            throw new IllegalArgumentException("The given name is too long");
        this.nomeCondutorAtual = nomeCondutorAtual;
    }

    @Override
    public void setTelefoneCondutorAtual(int telefoneCondutorAtual) {
        if (!Validation.telefoneValido(telefoneCondutorAtual))
            throw new IllegalArgumentException("Invalid phone number");
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

    @Override
    public String toString() {
        return String.format(
                "Veiculo(matricula=%s, nomeCondutorAtual=%s, telefoneCondutorAtual=%d, equipId=%d, frotaId=%d)",
                matricula, nomeCondutorAtual, telefoneCondutorAtual, equipamento.getId(), frota.getId()
        );
    }
}
